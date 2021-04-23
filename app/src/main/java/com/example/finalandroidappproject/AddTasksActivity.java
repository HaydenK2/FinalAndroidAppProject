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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

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
        Log.i(TAG, taskToAdd.toString());

        db.collection("TaskList").document("TaskObject").set(taskToAdd).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.i(TAG, "Success");
                    toastMessage("Task Addition Success");
                    finish();
                }
                else{
                    Log.i(TAG, "Failure");
                    toastMessage("Task Addition Failed");
                }
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

