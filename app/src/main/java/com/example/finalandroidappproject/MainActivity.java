package com.example.finalandroidappproject;
<<<<<<< Updated upstream

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

=======
>>>>>>> Stashed changes
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
//import android.support.v7.app.AlertDialog;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


//Referenced this to find the auth
// https://www.androidhive.info/2016/06/android-getting-started-firebase-simple-login-registration-auth/
public class MainActivity extends AppCompatActivity {
    // reference to entire database
    private static final String TAG = "EmailPassword";
    //Possible class that has all of firebase
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get Firebase Instance
        auth = FirebaseAuth.getInstance();
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        //bottomNav.setOnNavigationItemSelectedListener(navListener);
<<<<<<< Updated upstream
=======
        TextView textView = findViewById(R.id.textView4);
        Button button = findViewById(R.id.button3);
        String date =  getIntent().getStringExtra("date");
        if(date != null)
            textView.setText(date);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this,CalendarActivity.class);
                startActivity(intent);
            }
        });
>>>>>>> Stashed changes
    }
    /**
     * Referenced from this video and website
     * video: youtube.com/watch?v=tPV8xA7m-iw
     * website: https://codinginflow.com/tutorials/android/bottomnavigationview
     */
    /*private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch(item.getItemId()){
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                    return true;
                }
            };*/

    /** This function sends user to signUp activity
     * reference: https://learntodroid.com/how-to-switch-between-activities-in-android/
     */
    public void toSignUp(View v){
        Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    /**This function uses firebase and logs the user into the app
     * reference: https://firebase.google.com/docs/auth/android/password-auth?authuser=0
     * @param v
     */
<<<<<<< Updated upstream
    public void signIn(View v) {
        EditText emailEditText = (EditText) findViewById(R.id.textEmailAddress);
        EditText passEditText = (EditText) findViewById(R.id.textPassword);
=======

    public void signIn(View v) {
        EditText emailEditText = (EditText) findViewById(R.id.textEmailAddress);
        EditText passEditText = (EditText) findViewById(R.id.textPassword);

 /**   public void signIn(View v) {
        EditText emailEditText = (EditText) findViewById(R.id.editTextTextEmailAddress);
        EditText passEditText = (EditText) findViewById(R.id.editTextTextPassword);
>>>>>>> Stashed changes

        String email = emailEditText.getText().toString();
        String password = passEditText.getText().toString();
        Log.e("KIM", "Typing " + email + " and " + password);
        if(email.equals("") || password.equals("")){
            toastMessage("Please enter an email and/or password");
        }
        else{
            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmail:success");
                                FirebaseUser user = auth.getCurrentUser();
                                toastMessage("Authentication Success");
                                Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
                                startActivity(intent);
                            }
                            else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                toastMessage("Authentication Failed");

                            }
                        }
                    });
        }
    }



    /**
     * This method will be called to minimize the on screen keyboard in the Activity
     * When we get the current view, it is the view that has focus, which is the keyboard
     * Credit - Found and suggested by Ram Dixit, 2019
     *
     * Source:  https://www.youtube.com/watch?v=CW5Xekqfx3I
     */
    private void closeKeyboard() {
        View view = this.getCurrentFocus();     // view will refer to the keyboard
        if (view != null ){                     // if there is a view that has focus
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**This is an alert dialogue funciton
     * Source: https://suragch.medium.com/making-an-alertdialog-in-android-2045381e2edb
     * public void showAlertDialogue(View view, String str){
     *         AlertDialog.Builder builder = new AlertDialog.Builder(this);
     *         builder.setMessage(str);
     *         builder.setPositiveButton("OK", null);
     *         AlertDialog dialog = builder.create();
     *         dialog.show();
     *     }
     */
    /**
    public void showAlertDialogue(View view, String str){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(str);
        builder.setPositiveButton("OK", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**This function just makes writing toast messages easier
     *
     */
    /**
    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    //For main xml
    /*<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context="com.codinginflow.bottomnavigationviewexample.MainActivity">
        <FrameLayout
            android:id="@+id/fragment_container"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:layout_above="@id/bottom_navigation"/>
        <android.support.design.widget.BottomNavigationView
            android:id="@+id/bottom_navigation"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_alignParentBottom="true"
            app:menu="@menu/bottom_navigation"
            android:background="?android:attr/windowBackground"/>
    </RelativeLayout>*/
}



