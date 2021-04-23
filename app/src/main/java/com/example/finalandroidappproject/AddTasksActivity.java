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
    }

    public void addTask (View v) {
        EditText taskNameEditText = (EditText) findViewById(R.id.editTextTaskNameText);

        String taskName = taskNameEditText.getText().toString();

        Map<String, Object> task = new HashMap<String, Object>();

        task.put(NAME_TASK, taskName);
        Log.i(TAG, task.toString());
        db.collection("TaskList").document("JSR").set(task).addOnCompleteListener< new OnCompleteListener<Void>()>
    }

    //https://learntodroid.com/how-to-switch-between-activities-in-android/
    //goes back to previous activity
    public void goBack(View v){
        finish();
    }

}

