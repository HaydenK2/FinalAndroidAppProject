package com.example.finalandroidappproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class DisplayTasksActivity extends AppCompatActivity {

    private ArrayList<Task> myTasks;
    public static final String TAG = "DisplayTasksActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_tasks);

        // This Activity received an arraylist of all the events pulled from firebase to populate the ListView with
        Intent intent = getIntent();
        myTasks = intent.getParcelableArrayListExtra("tasks");

        // Get a reference to the ListView element to display all Events in firebase
        ListView allEventsListView = (ListView) findViewById(R.id.taskList);
        // CustomAdapter is an inner class defined below that details how to adapt this arraylist of data
        //CustomAdapter customAdapter = new CustomAdapter();
        //allEventsListView.setAdapter(customAdapter);
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