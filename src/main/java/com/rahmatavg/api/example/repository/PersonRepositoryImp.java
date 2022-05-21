package com.rahmatavg.api.example.repository;

import com.rahmatavg.api.example.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class PersonRepositoryImp implements PersonRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Person person) {
        String sql = "INSERT INTO public.person(name, age, birthday, money, is_deleted) VALUES (?, ?, ?, ?, ?);";
        return jdbcTemplate.update(sql, new Object[] {person.getName(), person.getAge(), person.getBirthday(), person.getMoney(), person.getDeleted()});
    }

    @Override
    public int update(Person person) {
        String sql = "UPDATE public.person SET name=?, age=?, birthday=?, money=?, is_deleted=? WHERE id=?;";
        return jdbcTemplate.update(sql, new Object[] {person.getName(), person.getAge(), new Timestamp(person.getBirthday().getTime()), person.getMoney(), person.getDeleted(), person.getId()});
    }

    @Override
    public Person findById(Long id) {
        String sql = "SELECT id, name, age, birthday, money, is_deleted FROM public.person WHERE id = ?";
        List<Person> result = jdbcTemplate.query(sql, new Object[] {id}, BeanPropertyRowMapper.newInstance(Person.class));
        return result.size() != 0 ? result.get(0) : null;
    }

    @Override
    public int deleteById(Long id) {
        String sql = "DELETE FROM public.person WHERE id = ?";
        return jdbcTemplate.update(sql, new Object[] {id});
    }

    @Override
    public List<Person> findAll() {
        String sql = "SELECT id, name, age, birthday, money, is_deleted FROM public.person";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Person.class));
    }
}
