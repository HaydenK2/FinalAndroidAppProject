package com.example.finalandroidappproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddTasksActivity extends AppCompatActivity {
    private static final String TAG = "taskadd";

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tasks);
    }

    public void addTask (View v) {
        EditText taskNameEditText = (EditText) findViewById(R.id.taskNameText);

        String taskName = taskNameEditText.getText().toString();

        db.collection("events")
                .add(taskName)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        //toastMessage("Event stored successfully");
                        Log.i(TAG, "Success");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //toastMessage("Event failed to add");
                        Log.i(TAG, "Failure");
                    }
                });
    }

    //https://learntodroid.com/how-to-switch-between-activities-in-android/
    //goes back to previous activity
    public void goBack(View v){
        finish();
    }

}

