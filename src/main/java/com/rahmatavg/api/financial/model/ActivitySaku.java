package com.rahmatavg.api.financial.model;

import java.util.Date;

public class ActivitySaku {
    private Long id;
    private Long userId;
    private Double moneys;
    private Integer activityTypeId;
    private String activityType;
    private String description;
    private Date createdAt;
    private Date updatedAt;
    private Double startBalanceTotal;
    private Double endBalanceTotal;
    private Date activityDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getMoneys() {
        return moneys;
    }

    public void setMoneys(Double moneys) {
        this.moneys = moneys;
    }

    public Integer getActivityTypeId() {
        return activityTypeId;
    }

    public void setActivityTypeId(Integer activityTypeId) {
        this.activityTypeId = activityTypeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Double getStartBalanceTotal() {
        return startBalanceTotal;
    }

    public void setStartBalanceTotal(Double startBalanceTotal) {
        this.startBalanceTotal = startBalanceTotal;
    }

    public Double getEndBalanceTotal() {
        return endBalanceTotal;
    }

    public void setEndBalanceTotal(Double endBalanceTotal) {
        this.endBalanceTotal = endBalanceTotal;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public Date getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }

    public ActivitySaku() {
    }

    @Override
    public String toString() {
        return "ActivitySaku{" +
                "id=" + id +
                ", userId=" + userId +
                ", moneys=" + moneys +
                ", activityTypeId=" + activityTypeId +
                ", activityType='" + activityType + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", startBalanceTotal=" + startBalanceTotal +
                ", endBalanceTotal=" + endBalanceTotal +
                ", activityDate=" + activityDate +
                '}';
    }
}
