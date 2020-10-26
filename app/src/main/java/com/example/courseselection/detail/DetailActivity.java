package com.example.courseselection.detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.courseselection.R;
import com.example.courseselection.entity.Course;
import com.example.courseselection.entity.Repository;
import com.example.courseselection.entity.Student;
import com.example.courseselection.utils.Constant;

import org.w3c.dom.Text;

import java.util.Formatter;

public class DetailActivity extends AppCompatActivity {

    private int studentId;

    private Course course;

    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        repository = Repository.getInstance();
        getFromIntent();
        initView();
        Button chooseButton = findViewById(R.id.choose_button);
        chooseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (repository.CourseHasStudent(studentId, course)) {
                    Toast.makeText(DetailActivity.this, "您已经选择该课程，请不要再点击", Toast.LENGTH_SHORT).show();
                } else {
                    course.addStudent(repository.getStudentById(studentId));
                    Toast.makeText(DetailActivity.this, "添加课程成功", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ImageView backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getFromIntent() {
        Intent intent = getIntent();
        studentId = intent.getIntExtra(Constant.STUDENT_ID, -1);
        long courseId = intent.getLongExtra(Constant.COURSE_ID, -1);
        course = repository.getCourseById(courseId);
    }

    private void initView() {
        TextView courseTitle = findViewById(R.id.course_title);
        TextView courseTeacher = findViewById(R.id.course_teacher);
        TextView courseScore = findViewById(R.id.course_score);
        TextView courseLessons = findViewById(R.id.course_lessons);
        TextView courseTime = findViewById(R.id.course_time);
        TextView courseAddress = findViewById(R.id.course_address);
        TextView courseDescription = findViewById(R.id.course_description);
        courseTitle.setText(course.getTitle());
        courseTeacher.setText(course.getTeacher());
        courseScore.setText("学分：" + new Formatter().format("%.1f", course.getScore()).toString());
        courseLessons.setText("课时：" + course.getLessons());
        courseTime.setText(course.getTime());
        courseAddress.setText(course.getAddress());
        courseDescription.setText(course.getDescription());
    }
}