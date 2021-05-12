package com.example.finalandroidappproject;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NavActivity extends DisplayTasksActivity {
    /**
     * Referenced from this video and website
     * video: youtube.com/watch?v=tPV8xA7m-iw
     * website: https://codinginflow.com/tutorials/android/bottomnavigationview
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new HomeFragment()).commit();
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    HomeFragment home = new HomeFragment();
                    CalendarFragment cal =  new CalendarFragment();
                    switch(item.getItemId()){
                        case R.id.nav_home:
                            selectedFragment = home;
                            break;
                        case R.id.nav_calendar:
                            selectedFragment = cal;
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                    return true;
                }
            };
}
