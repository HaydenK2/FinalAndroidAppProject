package com.example.finalandroidappproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;


import java.util.ArrayList;

public class DisplayTasksActivity extends AppCompatActivity {

    private ArrayList<TaskParcelable> myTasks;
    public static final String TAG = "DisplayTasksActivity";

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_tasks);

        db = FirebaseFirestore.getInstance();

        // This Activity received an arraylist of all the events pulled from firebase to populate the ListView with
        Intent intent = getIntent();
        myTasks = intent.getParcelableArrayListExtra("tasks");

        // Get a reference to the ListView element to display all Events in firebase
        ListView allTasksListView = (ListView) findViewById(R.id.taskList);

        // CustomAdapter is an inner class defined below that details how to adapt this arraylist of data
        CustomAdapter customAdapter = new CustomAdapter();
        allTasksListView.setAdapter(customAdapter);

        // Create a setOnItemClickListener for the listView to find out which element they clicked on

        //allTasksListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //@Override
            /**public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                TaskParcelable taskO = myTasks.get(i);
                Log.i(TAG, taskO.toString());

            }*/

            /**This function removes the task that's selected from the firebase firestore
             *
             * @param v
             */
            //public void removeTask(View v, int i){
                //db.collection("TaskList").document(myTasks.get(i).getKey()).delete();
            //}
       // });
    }

    /**This function sends the user to the acitivty where you can add tasks
     *
     * @param V
     */
    public void addTask(View V){
        Intent intent = new Intent(DisplayTasksActivity.this, AddTasksActivity.class);
        startActivity(intent);
    }


    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return myTasks.size();
        }

        @Override
        public Object getItem(int position) {return null; }

        @Override
        public long getItemId(int position) {return 0; }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            // attaches the custom xml design to this View you are creating
            view = getLayoutInflater().inflate(R.layout.activity_task_parcelable, null);

            // creates references to each element in the custom xml design.  That is why you
            // need to say view.findViewById because you have to reference the element that
            // was gotten from the LayoutInflater above
            TextView taskNameTV = (TextView) view.findViewById(R.id.taskName);

            // Here I am getting the specific element in the database we are currently displaying
            //TaskParcelable e = myTasks.get(i);

            // Set the correct image, event name, and event date for the Event object we are
            // displaying in the list
            //taskNameTV.setText(e.getTaskName());

            // return this view element with the correct data inserted
            return view;
        }
    }
}

