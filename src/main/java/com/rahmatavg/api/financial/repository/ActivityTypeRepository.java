package com.rahmatavg.api.financial.repository;

import com.rahmatavg.api.financial.model.ActivityType;

import java.util.List;

public interface ActivityTypeRepository {
    List<ActivityType> selectAllActivityType();
}
