package com.example.finalandroidappproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
    }

    public void addTask(View V){
        Intent intent = new Intent(TaskActivity.this, AddTasksActivity.class);
        startActivity(intent);
    }


}