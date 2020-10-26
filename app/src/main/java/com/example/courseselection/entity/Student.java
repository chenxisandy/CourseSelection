package com.example.courseselection.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Student implements Serializable {
    private int id;

    private String name;

    private String major;

    private String faculty;

    private String studentId;

    private String password;

    private List<Long> courseList = new ArrayList<>();

    public Student(int id, String name, String major, String faculty, String password) {
        this.id = id;
        this.name = name;
        this.major = major;
        this.faculty = faculty;
        this.studentId = "U" + id;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public List<Long> getCourseList() {
        return courseList;
    }

    public void addCourse(long courseId) {
        courseList.add(courseId);
    }

    public void removeCourse(long courseId) {
        courseList.remove(courseId);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
