package com.example.finalandroidappproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DisplayTasksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_tasks);
    }

    /**This function sends the user to the acitivty where you can add tasks
     *
     * @param V
     */
    public void addTask(View V){
        Intent intent = new Intent(DisplayTasksActivity.this, AddTasksActivity.class);
        startActivity(intent);
    }
}