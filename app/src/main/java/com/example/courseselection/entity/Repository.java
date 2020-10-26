package com.example.courseselection.entity;

import android.util.Log;

import com.example.courseselection.utils.Constant;

import java.util.ArrayList;
import java.util.List;

public class Repository {

    private final static String TAG = "Repo";

    private static Repository INSTANCE;

    public static Repository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Repository();
        }
        return INSTANCE;
    }

    private Repository() {}

    public List<Course> getCourseList() {
        return courseList;
    }


    private List<Student> studentList;

    private List<Course> courseList;

    public int checkStudentId(String studentId) {
        for (Student s :
                studentList) {
            if (s.getStudentId().equals(studentId)) {
                return s.getId();
            }
        }
        return Constant.ERROR;
    }

    public Student getStudentById(int id) {
        for (Student st :
                studentList) {
            if (st.getId() == id) {
                return st;
            }
        }
        return null;
    }

    public Course getCourseById(long id) {
        for (Course c :
                courseList) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    public boolean checkStudentPassword(int id, String pw) {
        if (id == Constant.ERROR) {
            Log.e(TAG, "checkStudentPassword: id=-1");
            return false;
        }
        Student student = getStudentById(id);
        if (student == null) {
            Log.e(TAG, "checkStudentPassword: student is null");
            return false;
        } else
            return student.getPassword().equals(pw);
    }

    public List<Course> getCoursesFromStudent(Student student) {
        List<Course> list = new ArrayList<>();
        for (long id :
                student.getCourseList()) {
            list.add(getCourseById(id));
        }
        return list;
    }

    public boolean CourseHasStudent(int studentId, Course course) {
        for (int id :
                course.getStudentList()) {
            if (id == studentId) {
                return true;
            }
        }
        return false;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }
}
