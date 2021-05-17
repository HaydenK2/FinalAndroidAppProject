package com.example.finalandroidappproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddTasksActivity extends AppCompatActivity {

    private static final String TAG = "taskadd";
    private TextView theDate;
    private Button btnGoCalendar;

    // Constants to use for labels in database
    public static final String NAME_TASK = "name";
    public static final String PRIORITY = "0";
    public static final String DATE = "mm/dd/yyyy";
    private FirebaseFirestore db;

    @Override
    /** Connecting CalendarView to AddTasksActivity
     * Video: https://www.youtube.com/watch?v=hHjFIG0TtA0
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_tasks);

        db = FirebaseFirestore.getInstance();

        theDate = (TextView) findViewById(R.id.editTimeText);

        btnGoCalendar = (Button) findViewById(R.id.calButton);

        Intent incomingIntent = getIntent();

        String date = incomingIntent.getStringExtra("date");

        theDate.setText(date);

        btnGoCalendar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddTasksActivity.this, CalendarActivity.class);
                startActivity(intent);
            }

        });
    }
    /** This function adds a task to the list of task on the TaskActivity Page
    /   reference: https://www.youtube.com/watch?v=y2op1D0W8oE
     */
    public void addTask (View v) {
        EditText taskNameEditText = (EditText) findViewById(R.id.editTextTaskNameText);
        EditText taskTimeEditText = (EditText) findViewById(R.id.editTimeText);

        String taskName = taskNameEditText.getText().toString();
        String taskTime = taskTimeEditText.getText().toString();

        /*//https://stackoverflow.com/questions/15037465/converting-edittext-to-int-android
        EditText priorityEditText = (EditText) findViewById(R.id.editTextPriorityText);
        int priorityInt = Integer.parseInt( priorityEditText.getText().toString());
        */


        //If either text boxes for task name or time are blank
        if(taskName.equals("") || taskTime.equals("")){
            toastMessage("Please enter a name and/or date");
        }

        else{

            Map<String, Object> taskToAdd = new HashMap<String, Object>();
            taskToAdd.put(NAME_TASK, taskName);
            taskToAdd.put(DATE, taskTime);

            Log.i(TAG, taskToAdd.toString());

            db.collection("TaskList").add(taskToAdd)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            toastMessage("Task stored successfully");
                            Log.i(TAG, "Success");
                            showData();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            toastMessage("Task failed to add");
                            Log.i(TAG, "Failure");
                        }
                    });
        }
    }


    /**This function puts the data from the firestore and places it on the DisplayTasksActivity.
     *
     */
    private void showData() {
        ArrayList<TaskParcelable> myTasks = new ArrayList<TaskParcelable>();
        db.collection("TaskList").orderBy(NAME_TASK).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document: task.getResult()) {
                        Log.i(TAG, document.getId() + " =>" + document.getData());
                        TaskParcelable t = new TaskParcelable(document.getString(NAME_TASK), document.getString(DATE), document.getId());
                        myTasks.add(t);
                    }
                }
                else {
                    Log.i(TAG, "Error getting documents", task.getException());
                }

                // Start new activity and send it the ArrayList of TaskParcelable objects
                //This part sends the array list we just made to the DisplayTasksActivity
                Intent intent = new Intent(AddTasksActivity.this, DisplayTasksActivity.class);
                intent.putExtra("tasks", myTasks);
                startActivity(intent);
            }
        });


    }

    /**this function goes back to previous activity
     * reference: https://learntodroid.com/how-to-switch-between-activities-in-android/
     * @param v
     */
    public void goBack(View v){
        showData();
    }

    /**This functions just makes writing toast messages easier
     *
     */
    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}

