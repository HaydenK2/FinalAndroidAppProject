package com.example.finalandroidappproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaPlayer;
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

    //Audio for when the user completes the task selected
    //https://www.youtube.com/watch?v=C_Ka7cKwXW0
    //Adds the *mp3 into Android file association
    //https://developer.clevertap.com/docs/add-a-sound-file-to-your-android-app#:~:text=Right%20click%20on%20Resources%20(res,file%20in%20the%20raw%20folder.
    MediaPlayer completeSoundMP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_tasks);

        //Alternate code
        //get reference of Fire store
        db = FirebaseFirestore.getInstance();


        //Defines the completeSoundMP
        completeSoundMP = MediaPlayer.create(this, R.raw.sus_sound);

        // This Activity received an arraylist of all the events pulled from firebase to populate the ListView with
        Intent intent = getIntent();
        myTasks = intent.getParcelableArrayListExtra("tasks");

        // Get a reference to the ListView element to display all Events in firebase
        ListView tasksListView = (ListView) findViewById(R.id.taskList);

        // CustomAdapter is an inner class defined below that details how to adapt this arraylist of data
        CustomAdapter customAdapter = new CustomAdapter();
        tasksListView.setAdapter(customAdapter);


        tasksListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                // Create a setOnItemClickListener for the listView to find out which element they clicked on
                tasksListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View v, int i, long l) {
                        TaskParcelable taskO = myTasks.get(i);

                        Log.i(TAG, taskO.getKey());
                        key = taskO.getKey();
                        toastMessage("Selected: " + taskO.getTaskName());

                        /*start an intent to load the page to edit this element that has been clicked on
                        Not going to use maybe?
                        Intent intent = new Intent(DisplayTasksActivity.this, DisplayTaskDetailsActivity.class);
                        intent.putExtra("taskParc", taskO);
                        startActivity(intent);*/
                    }
                });

            }

            /**This function removes the task that's selected from the firebase firestore
             *
             * @param v
             */
            public void removeTask(View v, int i){
                db.collection("TaskList").document(myTasks.get(i).getKey()).delete();
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


    /**This function is supposed to delete the selected task using the key of the task selected
     *
     */
    public void removeTask(View v){

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

    /**This function deletes the selected task using the key of the task selected and also plays a sound to congratulate user
     *
     * @param v
     */
    public void completeTask(View v){
        if(key.equals("")){
            toastMessage("Select first");
        }
        else{
            db.collection("TaskList").document(key).delete();
            toastMessage("Task completed");

            completeSoundMP.start();

            key ="";

            showData();
        }
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


            TaskParcelable e = myTasks.get(i);

            // Set the correct image, event name, and event date for the Event object we are
            // displaying in the list
            taskNameTV.setText(e.getTaskName());

            // return this view element with the correct data inserted
            return view;
        }


    }
}

