package com.rahmatavg.api.financial.model;

public class ActivityType {
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ActivityType() {
    }

    public ActivityType(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "ActivityType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
