package com.example.finalandroidappproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class WelcomeActivity extends AppCompatActivity {

    private static final String TAG = "EmailPassword";
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        db = FirebaseFirestore.getInstance();
    }

    /**This function puts the data from the firestore and places it on the DisplayTasksActivity.
     *
     * @param v
     */
    public void showData(View v) {
        ArrayList<TaskParcelable> myTasks = new ArrayList<TaskParcelable>();
        db.collection("TaskList").orderBy(AddTasksActivity.NAME_TASK).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document: task.getResult()) {
                        Log.i(TAG, document.getId() + " =>" + document.getData());
                        TaskParcelable t = new TaskParcelable(document.getString(AddTasksActivity.NAME_TASK), document.getId());
                        myTasks.add(t);
                    }
                }
                else {
                    Log.i(TAG, "Error getting documents", task.getException());
                }

                // Start new activity and send it the ArrayList of Event objects
                //This part sends the array list we just made to the DisplayEventsActivity
                //myTasks.add(t);
                Intent intent = new Intent(WelcomeActivity.this, DisplayTasksActivity.class);
                intent.putExtra("tasks", myTasks);
                startActivity(intent);
            }
        });


    }

    /**This functions just makes writing toast messages easier
     *
     */
    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}