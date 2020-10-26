package com.example.courseselection.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Course implements Serializable {

    private long id;

    private String teacher;

    private String title;

    private int lessons;    //课时

    private String time;

    private float score;    //学分

    private String address;

    private String description;

    private List<Integer> studentList = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.  id = id;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLessons() {
        return lessons;
    }

    public void setLessons(int lessons) {
        this.lessons = lessons;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public List<Integer> getStudentList() {
        return studentList;
    }

    public Course(long id, String teacher, String title, int lessons, String time, float score, String address, String description) {
        this.id = id;
        this.teacher = teacher;
        this.title = title;
        this.lessons = lessons;
        this.time = time;
        this.score = score;
        this.address = address;
        this.description = description;
    }

    public void addStudent(Student student) {
        studentList.add(student.getId());
        student.addCourse(this.id);
    }

    public void removeStudent(Student student) {
        studentList.remove(Integer.valueOf(student.getId()));
        student.removeCourse(id);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


