package com.rahmatavg.api.financial.repository;

import com.rahmatavg.api.financial.model.ActivityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ActivityTypeRepositoryImp implements ActivityTypeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<ActivityType> selectAllActivityType() {
        String sql = "SELECT id, name FROM public.actifity_type";
        return jdbcTemplate.query(sql, new RowMapper<ActivityType>() {
            @Override
            public ActivityType mapRow(ResultSet rs, int rowNum) throws SQLException {
                ActivityType data = new ActivityType();
                data.setId(rs.getLong("id"));
                data.setName(rs.getString("name"));
                return data;
            }
        });
    }
}
