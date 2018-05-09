package com.ebs.domain;

public class Course {

    private String courName;

    private Integer courCode;

    public Course() {
    }

    public Course(String courName, Integer courCode) {
        this.courName = courName;
        this.courCode = courCode;
    }

    public String getCourName() {
        return courName;
    }

    public void setCourName(String courName) {
        this.courName = courName;
    }

    public Integer getCourCode() {
        return courCode;
    }

    public void setCourCode(Integer courCode) {
        this.courCode = courCode;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courName='" + courName + '\'' +
                ", courCode='" + courCode + '\'' +
                '}';
    }
}
