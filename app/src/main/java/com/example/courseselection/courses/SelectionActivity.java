package com.example.courseselection.courses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.courseselection.R;
import com.example.courseselection.entity.Repository;
import com.example.courseselection.utils.Constant;

public class SelectionActivity extends AppCompatActivity {

    private Repository repo;

    private int studentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
        repo = Repository.getInstance();
        Intent intent = getIntent();
        studentId = intent.getIntExtra(Constant.STUDENT_ID, -1);

        initView();
    }

    private void initView() {
        RecyclerView recyclerView = findViewById(R.id.selection_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        CourseAdapter adapter = new CourseAdapter(repo.getCourseList(), studentId);
        recyclerView.setAdapter(adapter);

        ImageView back = findViewById(R.id.back_image);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}