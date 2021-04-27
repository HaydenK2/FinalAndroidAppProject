package com.example.finalandroidappproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {
    // reference to entire database
    private static final String TAG = "EmailPassword";

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Get Firebase Instance
        auth = FirebaseAuth.getInstance();
    }

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }
    }

    /**This function allows the user to create an account using the email and password they filled out on the sign up activity
     * reference: https://firebase.google.com/docs/auth/android/password-auth?authuser=0
     */
    /**public void signUp(View v) {

        EditText emailEditText = (EditText) findViewById(R.id.editTextTextEmailAddress);
        EditText passEditText = (EditText) findViewById(R.id.editTextTextPassword);

        String email = emailEditText.getText().toString();
        String password = passEditText.getText().toString();

        Log.e("KIM", "Typing " + email + " and " + password);

        if(email.equals("") || password.equals("")){
            toastMessage("Please enter an email and/or password");
        }
        else{
            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser user = auth.getCurrentUser();
                                //This code should switch back to previous acitivty it was called
                                finish();
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
        }

    }*/

    /**This function lets the user go back to previous screen
     *
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
}