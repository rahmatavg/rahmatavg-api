package com.rahmatavg.api.financial.repository;

import com.rahmatavg.api.financial.model.ActivitySaku;
import com.rahmatavg.api.financial.model.TotalActivitySakuResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Repository
public class ActivitySakuRepositoryImp implements ActivitySakuRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<ActivitySaku> selectActivitySakuPerDate(Map<String, Object> param) {
        Long userId = (Long) param.get("userId");
        Integer year = (Integer) param.get("year");
        Integer month = (Integer) param.get("month");

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT acs.id, acs.activity_date, acs.moneys, act.name AS activity_type, acs.description, acs.start_balance_total, acs.end_balance_total");
        sql.append(" FROM public.actifity_saku acs");
        sql.append(" INNER JOIN public.actifity_type act ON act.id = acs.activity_type_id");
        sql.append(" WHERE EXTRACT(YEAR FROM acs.created_at) = "+year+" AND EXTRACT(MONTH FROM acs.created_at) = "+month+" AND acs.user_id = "+userId);
        sql.append(" ORDER BY acs.created_at");
        return jdbcTemplate.query(sql.toString(), new RowMapper<ActivitySaku>() {
            @Override
            public ActivitySaku mapRow(ResultSet rs, int rowNum) throws SQLException {
                ActivitySaku data = new ActivitySaku();
                data.setId(rs.getLong("id"));
                data.setActivityDate(rs.getDate("activity_date") == null ? null : new Timestamp(rs.getDate("activity_date").getTime()));
                data.setMoneys(rs.getDouble("moneys"));
                data.setActivityType(rs.getString("activity_type"));
                data.setDescription(rs.getString("description"));
                data.setStartBalanceTotal(rs.getDouble("start_balance_total"));
                data.setEndBalanceTotal(rs.getDouble("end_balance_total"));
                return data;
            }
        });
    }

    @Override
    public TotalActivitySakuResponse selectTotalActivitySaku(Long userSid) {
        StringBuilder sql = new StringBuilder();
        sql.append(" WITH data_duit AS (");
        sql.append(" SELECT * FROM actifity_saku WHERE user_id = "+userSid);
        sql.append(" ), saldo AS (");
        sql.append(" SELECT COALESCE(SUM(moneys)) AS saldo FROM data_duit");
        sql.append(" ), pemasukan AS (");
        sql.append(" SELECT COALESCE(SUM(moneys)) AS pemasukan FROM data_duit WHERE activity_type_id = 1");
        sql.append(" ), pengeluaran AS (");
        sql.append(" SELECT COALESCE(SUM(ABS(moneys)), 0) AS pengeluaran FROM data_duit WHERE activity_type_id = 2");
        sql.append(" )");
        sql.append(" SELECT a.*, b.*, c.*");
        sql.append(" FROM saldo a, pemasukan b, pengeluaran c");

        List<TotalActivitySakuResponse> result = jdbcTemplate.query(sql.toString(), new RowMapper<TotalActivitySakuResponse>() {
            @Override
            public TotalActivitySakuResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                TotalActivitySakuResponse data = new TotalActivitySakuResponse();
                data.setBalance(rs.getDouble("saldo"));
                data.setTotalIncome(rs.getDouble("pemasukan"));
                data.setTotalExpenditure(rs.getDouble("pengeluaran"));
                return data;
            }
        });
        return result.size() != 0 ? result.get(0) : null;
    }

    @Override
    public int saveActivitySaku(ActivitySaku activitySaku) {
        StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO public.actifity_saku(user_id, moneys, activity_type_id, description, created_at, updated_at, start_balance_total, end_balance_total, activity_date)");
        sql.append(" VALUES (?, ?, ?, ?, NOW(), NOW(), ?, ?, ?)");
        return jdbcTemplate.update(sql.toString(), new Object[] {
                activitySaku.getUserId(),
                activitySaku.getMoneys(),
                activitySaku.getActivityTypeId(),
                activitySaku.getDescription(),
                activitySaku.getStartBalanceTotal(),
                activitySaku.getEndBalanceTotal(),
                new Timestamp(activitySaku.getActivityDate().getTime())
        });
    }

    @Override
    public Double selectLastMoneys(Long userId) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT COALESCE(SUM(moneys), 0) AS moneys FROM actifity_saku WHERE user_id = ").append(userId);
        return jdbcTemplate.queryForObject(sql.toString(), Double.class);
    }

    @Override
    public int updateActivitySaku(ActivitySaku activitySaku) {
        String sql = "UPDATE public.actifity_saku SET moneys = ?, description = ?, end_balance_total=?, updated_at=NOW() WHERE id = ?";
        return jdbcTemplate.update(sql, new Object[] {
                activitySaku.getMoneys(),
                activitySaku.getDescription(),
                activitySaku.getEndBalanceTotal(),
                activitySaku.getId()
        });
    }

    @Override
    public int deleteActivitySaku(Long idActivitySaku, Long userId) {
        String sql = "DELETE FROM public.actifity_saku WHERE id = ? AND user_id = ?";
        return jdbcTemplate.update(sql, new Object[] {idActivitySaku, userId});
    }
}
