package com.example.finalandroidappproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Referenced from this video and website
 * video: youtube.com/watch?v=tPV8xA7m-iw
 * website: https://codinginflow.com/tutorials/android/bottomnavigationview
 */
public class HomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_display_tasks, container, false);
    }
}
