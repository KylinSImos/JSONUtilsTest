package com.ebs.domain;

import java.util.List;

public class Teacher {

    private String teaName;

    private Integer teaAge;

    private List<Student> teaStus;

    private Course teaCours;

    public Teacher() {
    }

    public Teacher(String teaName, Integer teaAge, List<Student> teaStus, Course teaCours) {
        this.teaName = teaName;
        this.teaAge = teaAge;
        this.teaStus = teaStus;
        this.teaCours = teaCours;
    }

    public String getTeaName() {
        return teaName;
    }

    public void setTeaName(String teaName) {
        this.teaName = teaName;
    }

    public Integer getTeaAge() {
        return teaAge;
    }

    public void setTeaAge(Integer teaAge) {
        this.teaAge = teaAge;
    }

    public List<Student> getTeaStus() {
        return teaStus;
    }

    public void setTeaStus(List<Student> teaStus) {
        this.teaStus = teaStus;
    }

    public Course getTeaCours() {
        return teaCours;
    }

    public void setTeaCours(Course teaCours) {
        this.teaCours = teaCours;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teaName='" + teaName + '\'' +
                ", teaAge='" + teaAge + '\'' +
                ", teaStus=" + teaStus +
                ", teaCours=" + teaCours +
                '}';
    }
}
