package com.rahmatavg.api.login.repository;

import com.rahmatavg.api.example.model.Person;
import com.rahmatavg.api.example.repository.PersonRepository;
import com.rahmatavg.api.login.model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class LoginRepositoryImp implements LoginRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Login findByEmail(String email) {
        String sql = "SELECT id, name, email, password FROM public.users WHERE email = ?";
        List<Login> result = jdbcTemplate.query(sql, new Object[]{email}, new RowMapper<Login>() {
            @Override
            public Login mapRow(ResultSet rs, int rowNum) throws SQLException {
                Login data = new Login(
                        rs.getLong("id"),
                        rs.getString("email"),
                        rs.getString("name"),
                        null,
                        null,
                        rs.getString("password")
                );
                return data;
            }
        });
        return result.size() != 0 ? result.get(0) : null;
    }
}
