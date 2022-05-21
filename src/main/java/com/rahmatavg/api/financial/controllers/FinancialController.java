package com.rahmatavg.api.financial.controllers;

import com.rahmatavg.api.financial.model.ActivitySaku;
import com.rahmatavg.api.financial.model.ActivityType;
import com.rahmatavg.api.financial.model.TotalActivitySakuResponse;
import com.rahmatavg.api.financial.repository.ActivitySakuRepository;
import com.rahmatavg.api.financial.repository.ActivityTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/financial")
public class FinancialController {

    @Autowired
    ActivityTypeRepository activityTypeRepository;

    @Autowired
    ActivitySakuRepository activitySakuRepository;

    @RequestMapping(value="activity_type", method = RequestMethod.GET)
    public ResponseEntity<List<ActivityType>> activityType() {
        List<ActivityType> activityTypeList = activityTypeRepository.selectAllActivityType();
        return new ResponseEntity<>(activityTypeList, HttpStatus.OK);
    }

    @RequestMapping(value="activity", method = RequestMethod.GET)
    public ResponseEntity<List<ActivitySaku>> activitySaku(@RequestParam("userId") Long userId) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        Integer year = c.get(Calendar.YEAR);
        Integer month = c.get(Calendar.MONTH);

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userId", userId);
        param.put("year", year);
        param.put("month", month+1);
        List<ActivitySaku> activitySakuList = activitySakuRepository.selectActivitySakuPerDate(param);
        return new ResponseEntity<>(activitySakuList, HttpStatus.OK);
    }

    @RequestMapping(value = "total", method = RequestMethod.GET)
    public ResponseEntity<TotalActivitySakuResponse> totalActivitySaku(@RequestParam("userId") Long userId) {
        TotalActivitySakuResponse totalActivitySakuResponse = activitySakuRepository.selectTotalActivitySaku(userId);
        return new ResponseEntity<>(totalActivitySakuResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ResponseEntity<String> createActivitySaku(@RequestBody ActivitySaku param) {
        try {

            ActivitySaku activitySaku = new ActivitySaku();
            activitySaku.setUserId(param.getUserId());
            activitySaku.setMoneys(param.getMoneys());
            activitySaku.setActivityTypeId(param.getActivityTypeId());
            activitySaku.setDescription(param.getDescription());

            Double startBalance = activitySakuRepository.selectLastMoneys(activitySaku.getUserId());
            activitySaku.setStartBalanceTotal(startBalance);
            activitySaku.setEndBalanceTotal(startBalance+activitySaku.getMoneys());
            int result = activitySakuRepository.saveActivitySaku(activitySaku);
            if (result == 0) {
                return new ResponseEntity<>("error", HttpStatus.OK);
            }
            return new ResponseEntity<>("y", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ResponseEntity<String> updateActivitySaku(@RequestBody ActivitySaku param) {
        ActivitySaku activitySaku = new ActivitySaku();
        activitySaku.setId(param.getId());
        activitySaku.setUserId(param.getUserId());
        activitySaku.setMoneys(param.getMoneys());
        activitySaku.setDescription(param.getDescription());
        activitySaku.setStartBalanceTotal(param.getStartBalanceTotal());
        activitySaku.setEndBalanceTotal(activitySaku.getStartBalanceTotal()+activitySaku.getMoneys());

        int result = activitySakuRepository.updateActivitySaku(activitySaku);
        if (result == 0) {
            return new ResponseEntity<>("error", HttpStatus.OK);
        }
        return new ResponseEntity<>("y", HttpStatus.OK);
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public ResponseEntity<String> deleteActivitySaku(@RequestParam("userId") Long userId, @RequestParam("id") Long id) {
        int result = activitySakuRepository.deleteActivitySaku(id, userId);
        if (result == 0) {
            return new ResponseEntity<>("error", HttpStatus.OK);
        }
        return new ResponseEntity<>("y", HttpStatus.OK);
    }

}
