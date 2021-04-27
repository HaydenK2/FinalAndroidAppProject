package com.example.finalandroidappproject;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;

import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;

public class CalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        Calendar beginTime = Calendar.getInstance();
        beginTime.set(2012, 0, 19, 7, 30);
        Calendar endTime = Calendar.getInstance();
        endTime.set(2012, 0, 19, 8, 30);
        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI);
        /**        .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis())
         .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis())
         .putExtra(CalendarContract.Events.TITLE, "Yoga")
         .putExtra(CalendarContract.Events.DESCRIPTION, "Group class")
         .putExtra(CalendarContract.Events.EVENT_LOCATION, "The gym")
         .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY)
         .putExtra(Intent.EXTRA_EMAIL, "rowan@example.com,trevor@example.com");*/
        startActivity(intent);
    }

    public void addTask(View V){
        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setData(CalendarContract.Events.CONTENT_URI);
        //startActivity();
    }
}


