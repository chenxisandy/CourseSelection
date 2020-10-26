package com.example.courseselection.courses;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.courseselection.detail.DetailActivity;
import com.example.courseselection.R;
import com.example.courseselection.entity.Course;
import com.example.courseselection.utils.Constant;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder>{

    private List<Course> courseList;

    private Context mContext;

    private int studentId;

    public CourseAdapter(List<Course> courseList, int studentId) {
        this.courseList = courseList;
        this.studentId = studentId;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.course_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Course course = courseList.get(position);
        holder.textTitle.setText(course.getTitle());
        holder.textTeacher.setText(course.getTeacher());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra(Constant.STUDENT_ID, studentId);
                intent.putExtra(Constant.COURSE_ID, course.getId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;

        TextView textTitle;

        TextView textTeacher;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_view);
            textTitle = itemView.findViewById(R.id.title_text);
            textTeacher = itemView.findViewById(R.id.teacher_text);
        }
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }
}
