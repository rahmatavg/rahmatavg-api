package com.rahmatavg.api.financial.repository;

import com.rahmatavg.api.financial.model.ActivitySaku;
import com.rahmatavg.api.financial.model.TotalActivitySakuResponse;

import java.util.List;
import java.util.Map;

public interface ActivitySakuRepository {
    List<ActivitySaku> selectActivitySakuPerDate(Map<String, Object> param);
    TotalActivitySakuResponse selectTotalActivitySaku(Long userSid);
    int saveActivitySaku(ActivitySaku activitySakuRepository);
    Double selectLastMoneys(Long userId);
    int updateActivitySaku(ActivitySaku activitySakuRepository);
    int deleteActivitySaku(Long idActivitySaku, Long userId);
}
