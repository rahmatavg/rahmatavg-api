package com.rahmatavg.api.login.repository;

import com.rahmatavg.api.login.model.GenerateToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class GenerateTokenRepositoryImp implements GenerateTokenRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int saveToken(GenerateToken generateToken) {
        String sql = "INSERT INTO public.generate_token(user_id, token) VALUES (?, ?)";
        return jdbcTemplate.update(sql, new Object[] {generateToken.getUserId(), generateToken.getToken()});
    }

    @Override
    public String getToken(Long userId) {
        String sql = "SELECT token FROM generate_token WHERE user_id = ?";
        List<String> result = jdbcTemplate.query(sql, new Object[] {userId}, new RowMapper<String>() {

            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("token");
            }

        });
        return result.size() != 0 ? result.get(0) : null;
    }

    @Override
    public int deleteToken(Long userId) {
        String sql = "DELETE FROM public.generate_token WHERE user_id = ?";
        return jdbcTemplate.update(sql, new Object[] {userId});
    }
}
