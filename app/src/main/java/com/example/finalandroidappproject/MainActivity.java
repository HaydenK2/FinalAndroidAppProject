package com.example.finalandroidappproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {
    // reference to entire database
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void signIn(View v) {
        EditText emailEditText = (EditText) findViewById(R.id.textEmailAddress);
        EditText passEditText = (EditText) findViewById(R.id.textPassword);


        String email = emailEditText.getText().toString();
        String password = passEditText.getText().toString();
        Log.e("KIM", "Typing " + email + " and " + password);
    }
}



