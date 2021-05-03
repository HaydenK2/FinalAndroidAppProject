package com.example.finalandroidappproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class FirebaseTaskActivity extends AppCompatActivity {
    public static final String TAG = "DisplayTasksActivity";

    private FirebaseFirestore db;

    // Constants to use for labels in database
    public static final String NAME_TASK = "name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_task);

        //get reference of Firestore
        db = FirebaseFirestore.getInstance();
    }

    private void showData() {
        ArrayList<TaskParcelable> myTasks = new ArrayList<TaskParcelable>();
        db.collection("TaskList").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.i(TAG, document.getId() + " =>" + document.getData());
                        TaskParcelable t = new TaskParcelable(document.getString(NAME_TASK), document.getId());
                        myTasks.add(t);
                    }
                } else {
                    Log.i(TAG, "Error getting documents", task.getException());
                }

                // Start new activity and send it the ArrayList of TaskParcelable objects
                //This part sends the array list we just made to the DisplayTasksActivity
                Intent intent = new Intent(FirebaseTaskActivity.this, DisplayTasksActivity.class);
                intent.putExtra("tasks", myTasks);



                startActivity(intent);
            }
        });
    }
}