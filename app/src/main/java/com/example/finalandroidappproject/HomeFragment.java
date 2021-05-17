package com.example.finalandroidappproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Referenced from this video and website
 * video: youtube.com/watch?v=tPV8xA7m-iw
 * website: https://codinginflow.com/tutorials/android/bottomnavigationview
 */
public class HomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_display_tasks, container, false);
        ArrayList<String> output = null;
        ListView tasksListView = (ListView) view.findViewById(R.id.taskList);

        /**
         * This displays the tasks of the user into the listview
         */
        ArrayList<TaskParcelable> allTasks = getTasks.getMyTasks();
        for(int i = 0; i < allTasks.size(); i++){
            output.add("String: " + allTasks.get(i).getTaskName()+ "\nDate: " + allTasks.get(i).getTaskDate());
        }
        ArrayAdapter<String> listViewAdapter= new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,output);


        tasksListView.setAdapter(listViewAdapter);
        return view;
    }
}

/**
 * This class imports the arraylist of the tasks from DisplayTasksActivity()
 * link: https://youtu.be/edZwD54xfbk
 */
class getTasks extends DisplayTasksActivity{
    public static ArrayList<TaskParcelable> tasks(){
        return getMyTasks();
    };
};
