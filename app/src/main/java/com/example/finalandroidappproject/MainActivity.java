package com.example.finalandroidappproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;



//Referenced this to find the auth
// https://www.androidhive.info/2016/06/android-getting-started-firebase-simple-login-registration-auth/
public class MainActivity extends AppCompatActivity {
    // reference to entire database
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get Firebase Instance
        auth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }
    }

    //https://firebase.google.com/docs/auth/android/password-auth?authuser=0
    public void signUp(View v) {
        EditText emailEditText = (EditText) findViewById(R.id.textEmailAddress);
        EditText passEditText = (EditText) findViewById(R.id.textPassword);

        String email = emailEditText.getText().toString();
        String password = passEditText.getText().toString();
        Log.e("KIM", "Typing " + email + " and " + password);

        //Successful -> .then code
        //Unsuccessful -> catch code (catch error message)

        auth.createUserWithEmailAndPassword(email, password).
                addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.e("KIM", "createUserWithEmail:success");
                            FirebaseUser user = auth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.e("KIM", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });
    }

    /*public void signIn(View v) {
        EditText emailEditText = (EditText) findViewById(R.id.textEmailAddress);
        EditText passEditText = (EditText) findViewById(R.id.textPassword);


        String email = emailEditText.getText().toString();
        String password = passEditText.getText().toString();
        Log.e("KIM", "Typing " + email + " and " + password);

        //Successful -> .then code
        //Unsuccessful -> catch code (catch error message)
        auth.signInWithEmailAndPassword(email, password).then(() => {
                console.log("Signed in " + email);


        }).catch(e => Log.e(e.message));
    }*/
}



