package com.rahmatavg.api.example.model;

import java.util.Date;

public class Person {
    private Long id;
    private String name;
    private Integer age;
    private Date birthday;
    private Double money;
    private Boolean isDeleted;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Person() {
    }

    public Person(Long id, String name, Integer age, Date birthday, Double money, Boolean isDeleted) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.birthday = birthday;
        this.money = money;
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                ", money=" + money +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
