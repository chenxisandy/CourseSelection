package com.example.courseselection.courses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.courseselection.R;
import com.example.courseselection.entity.Repository;
import com.example.courseselection.entity.Student;
import com.example.courseselection.utils.Constant;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CourseActivity extends AppCompatActivity {

    private Repository repo;

    private Student student;

    private CourseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        repo = Repository.getInstance();
        Intent intent = getIntent();
        final int studentId = intent.getIntExtra(Constant.STUDENT_ID, -1);
        student = repo.getStudentById(studentId);
        initView();

        FloatingActionButton addFab = findViewById(R.id.fab_add);
        addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(CourseActivity.this, SelectionActivity.class);
                intent1.putExtra(Constant.STUDENT_ID, studentId);
                startActivity(intent1);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.setCourseList(repo.getCoursesFromStudent(student));
        adapter.notifyDataSetChanged();
    }

    private void initView() {
        RecyclerView recyclerView = findViewById(R.id.course_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CourseAdapter(repo.getCoursesFromStudent(student), student.getId());
        recyclerView.setAdapter(adapter);

        TextView nameText = findViewById(R.id.name_text);
        nameText.setText(student.getName());
//        mAdapter = new AccountAdapter(list);
//        recyclerView.setAdapter(mAdapter);
    }
}