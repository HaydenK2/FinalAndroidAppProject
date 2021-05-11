package com.example.finalandroidappproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DisplayTaskDetailsActivity extends AppCompatActivity {


    private static final String TAG = "TaskDetails";

    private FirebaseFirestore db;

    private String selectedKey;

    private boolean isComplete;

    // Constants to use for labels in database
    public static final String NAME_TASK = "name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_task_details);

        //get reference of Firestore
        db = FirebaseFirestore.getInstance();

        isComplete = false;

        Intent intent = getIntent();
        TaskParcelable selectedT = intent.getParcelableExtra("taskParc");

        selectedKey = selectedT.getKey();

        TextView taskNameTV = (TextView) findViewById(R.id.taskNameText);
        taskNameTV.setText(selectedT.getTaskName());
    }

    /**Removes task from the firestore and from the displayTasksActivity
     *Do something else too (Add a point or something?)
     *
     * @param v
     */
    public void completeTask(View v){
        db.collection("TaskList").document(selectedKey).delete();
        toastMessage("Task Completed");
        showData();
    }


    /**Removes task from the firestore and from the displayTasksActivity
     *
     * @param v
     */
    public void removeTask(View v){
        db.collection("TaskList").document(selectedKey).delete();
        toastMessage("Task Removed");
        showData();
    }

    /**Return to the previous activity user was on
     *
     * @param v
     */
    public void goBack(View v){
        finish();
    }

    /**This function just makes writing toast messages easier
     *
     */
    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    /**This function puts the data from the firestore and places it on the DisplayTasksActivity.
     *
     */
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
                Intent intent = new Intent(DisplayTaskDetailsActivity.this, DisplayTasksActivity.class);
                intent.putExtra("tasks", myTasks);



                startActivity(intent);
            }
        });
    }
}