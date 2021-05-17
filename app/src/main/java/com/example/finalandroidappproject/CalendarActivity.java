package com.example.finalandroidappproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class CalendarActivity extends AppCompatActivity {
    private static final String TAG = "CalendarActivity";
    private CalendarView calView;
    private TimePicker timePicker1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        /**
         * Time Picker
         * link: https://www.tutorialspoint.com/android/android_timepicker_control.htm#:~:text=Android%20Time%20Picker%20allows%20you,this%20functionality%20through%20TimePicker%20class
         */
        //timePicker1 = (TimePicker) findViewById(R.id.timePicker1);
        calView = findViewById(R.id.calendarView);
        calView.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2){
                String date = (i1+1) + "/" + i2 + "/" + i;

                Intent intent = new Intent(CalendarActivity.this, AddTasksActivity.class);
                intent.putExtra("date", date);
                startActivity(intent);
            }
        });
    }
}