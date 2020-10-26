package com.example.courseselection.entity;

import java.io.Serializable;
import java.util.List;

public class DataBean implements Serializable {
    private List<Student> students;

    private List<Course> courses;

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
