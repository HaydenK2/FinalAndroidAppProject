package com.example.finalandroidappproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class DisplayTasksActivity extends AppCompatActivity {

    private ArrayList<TaskParcelable> myTasks;
    public static final String TAG = "DisplayTasksActivity";

    //tried something; leave in for now
    private FirebaseFirestore db;
    // Constants to use for labels in database
    public static final String NAME_TASK = "name";


    private ArrayAdapter adapter;
    private String key = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_tasks);

        //Alternate code
        //get reference of Firestore
        db = FirebaseFirestore.getInstance();

        // This Activity received an arraylist of all the events pulled from firebase to populate the ListView with
        Intent intent = getIntent();
        myTasks = intent.getParcelableArrayListExtra("tasks");

        // Get a reference to the ListView element to display all Events in firebase
        ListView tasksListView = (ListView) findViewById(R.id.taskList);

        // CustomAdapter is an inner class defined below that details how to adapt this arraylist of data
        CustomAdapter customAdapter = new CustomAdapter();
        tasksListView.setAdapter(customAdapter);



        // Create a setOnItemClickListener for the listView to find out which element they clicked on
        tasksListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View v, int i, long l) {
                TaskParcelable taskO = myTasks.get(i);
                Log.i(TAG, taskO.getKey());

                key = taskO.getKey();
                /*
                // start an intent to load the page to edit this element that has been clicked on
                Intent intent = new Intent(DisplayTasksActivity.this, DisplayTaskDetailsActivity.class);
                intent.putExtra("taskParc", taskO);
                startActivity(intent);*/
            }
        });

    }

    /**This function sends the user to the activity where you can add tasks
     *
     * @param V
     */
    public void addTask(View V){
        Intent intent = new Intent(DisplayTasksActivity.this, AddTasksActivity.class);
        startActivity(intent);
    }

    /**This function just makes writing toast messages easier
     *
     */
    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    /**This function is supposed to delete the selected task, but Idk if this will work.
     *
     */
    public void removeTask(View v){
        //Get task obj

        if(key.equals("")){
            toastMessage("Select first");
        }
        else{
            db.collection("TaskList").document(key).delete();
            toastMessage("Task Removed");
            key ="";
            showData();
        }
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
                Intent intent = new Intent(DisplayTasksActivity.this, DisplayTasksActivity.class);
                intent.putExtra("tasks", myTasks);
                startActivity(intent);
            }
        });
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

        /**
         *Documentation:
         * https://developer.android.com/reference/android/widget/Adapter.html#getView(int,%20android.view.View,%20android.view.ViewGroup)
         *
         *
         * Get a View that displays the data at the specified position in the data set.
         * You can either create a View manually or inflate it from an XML layout file.
         * When the View is inflated, the parent View (GridView, ListView...) will apply
         * default layout parameters unless you use LayoutInflater.inflate(int, android.view.ViewGroup,
         * boolean) to specify a root view and to prevent attachment to the root.
         *
         * @param i         The element in the array you are displaying
         * @param view      The old view to reuse if possible.
         * @param viewGroup The parent group that this view will eventually be attached to
         * @return          The correct view laid out according to the xml file with the
         *                  data from the current entry i
         */
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            // attaches the custom xml design to this View you are creating
            view = getLayoutInflater().inflate(R.layout.activity_task_parcelable, null);

            // creates references to each element in the custom xml design.  That is why you
            // need to say view.findViewById because you have to reference the element that
            // was gotten from the LayoutInflater above
            TextView taskNameTV = (TextView) view.findViewById(R.id.taskName);

            // Here I am getting the specific element in the database we are currently displaying
            TaskParcelable t = myTasks.get(i);

            // Set the correct image, event name, and event date for the Event object we are
            // displaying in the list
            taskNameTV.setText(t.getTaskName());


            // return this view element with the correct data inserted
            return view;
        }


    }
}

