package com.example.courseselection.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.courseselection.courses.CourseActivity;
import com.example.courseselection.R;
import com.example.courseselection.entity.Course;
import com.example.courseselection.entity.DataBean;
import com.example.courseselection.entity.Repository;
import com.example.courseselection.entity.Student;
import com.example.courseselection.utils.Constant;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText studentId;

    private EditText password;

    private Repository repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        repo = Repository.getInstance();
        //读取数据
        readTest();
        initView();
    }

    private void initView() {
        studentId = findViewById(R.id.id);
        password = findViewById(R.id.password);
        Button buttonLogin = findViewById(R.id.login_button);
        buttonLogin.setOnClickListener(this);
    }

    private void checkLogin() {
        int id = repo.checkStudentId(studentId.getText().toString());
        if (id == Constant.ERROR) {
            Toast.makeText(this, "学号不存在", Toast.LENGTH_SHORT).show();
        } else if (!repo.checkStudentPassword(id, password.getText().toString())){
            Toast.makeText(this, "密码错误", Toast.LENGTH_SHORT).show();
        } else {
            turnToCourseSelection(id);
        }
    }

    private void turnToCourseSelection(int id) {
        Intent intent = new Intent(this, CourseActivity.class);
        intent.putExtra(Constant.STUDENT_ID, id);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_button:
                Toast.makeText(this, "log", Toast.LENGTH_SHORT).show();
                checkLogin();
                //jsonTest();
                //readTest();
                break;
        }
    }

    // TODO: 2020/10/26  to delete
    private void jsonTest() {
        DataBean dataBean = new DataBean();
        List<Student> studentList = new ArrayList<>();
        List<Course> courseList = new ArrayList<>();
        Student student = new Student(201817142, "朱孝曦", "软件工程", "软件学院", "123");
        Student student1 = new Student(201817143, "鲁清新", "软件工程", "软件学院", "456");
        Course course = new Course(1, "黄浩", "计算机组成原理", 30, "周一周三第一节",
                1.5f, "东十二315", "啦啦啦良好的萨孤独与诗贯穿于各大已超过");
        Course course1 = new Course(1, "不知道", "华科组成原理", 40, "周一周三第一节",
                2.0f, "东九D301", "啦啦啦啦啦啦啦啦啦啦啦啦，你是卖报的小行家");
        course.addStudent(student);
        course1.addStudent(student);
        course1.addStudent(student1);
        dataBean.setCourses(courseList);
        dataBean.setStudents(studentList);
        Gson gson = new Gson();
        String string = gson.toJson(course1);
        Log.d("Test Json", string);
    }

    private void readTest() {
        //从assets获取json文件
        InputStreamReader isr = new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("assets/" + "data2.json"));
        //字节流转字符流
        BufferedReader bfr = new BufferedReader(isr);
        String line = "";
        StringBuilder stringBuilder = new StringBuilder();
        while (true){
            try {
                if (!((line = bfr.readLine())!=null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            stringBuilder.append(line);
        }//将JSON数据转化为字符串
        String string = stringBuilder.toString();
        Gson gson = new Gson();
        DataBean dataBean = gson.fromJson(string, DataBean.class);
        repo.setCourseList(dataBean.getCourses());
        repo.setStudentList(dataBean.getStudents());
    }
}