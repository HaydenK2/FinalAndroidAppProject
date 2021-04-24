package com.example.finalandroidappproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

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

    // Constants to use for labels in database
    public static final String NAME_TASK = "name";

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tasks);

        db = FirebaseFirestore.getInstance();
    }

    /** This function adds a task to the list of task on the TaskActivity Page
    /   reference:https://www.youtube.com/watch?v=y2op1D0W8oE
     */
    public void addTask (View v) {
        EditText taskNameEditText = (EditText) findViewById(R.id.editTextTaskNameText);

        String taskName = taskNameEditText.getText().toString();
        Map<String, Object> taskToAdd = new HashMap<String, Object>();
        taskToAdd.put(NAME_TASK, taskName);

        db.collection("TaskList").add(taskToAdd).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
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


    /**This function puts the data from the firestore and places it on the DisplayTasksActivity.
     *
     */
    public void showData() {
        ArrayList<TaskParcelable> myTasks = new ArrayList<TaskParcelable>();
        db.collection("TaskList").orderBy(NAME_TASK).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document: task.getResult()) {
                        Log.i(TAG, document.getId() + " =>" + document.getData());
                        TaskParcelable t = new TaskParcelable(document.getString(NAME_TASK), document.getId());
                        myTasks.add(t);
                    }
                }
                else {
                    Log.i(TAG, "Error getting documents", task.getException());
                }

                // Start new activity and send it the ArrayList of Event objects
                //This part sends the array list we just made to the DisplayEventsActivity
                //myTasks.add(t);
                Intent intent = new Intent(AddTasksActivity.this, DisplayTasksActivity.class);
                intent.putExtra("tasks", myTasks);
                startActivity(intent);
            }
        });


    }


    //https://learntodroid.com/how-to-switch-between-activities-in-android/
    //goes back to previous activity
    public void goBack(View v){
        finish();
    }

    /**This functions just makes writing toast messages easier
     *
     */
    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}

