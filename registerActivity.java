package com.example.zahut.easyrecipe;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

public class registerActivity extends AppCompatActivity {

    private Button finalRegisterBtn;
    private EditText emailRegistrationInput, passwordRegistrationInput;

    private FirebaseAuth userAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        finalRegisterBtn = (Button) findViewById(R.id.finalRegistrationBtn);
        emailRegistrationInput = (EditText) findViewById(R.id.registrationEmail);
        passwordRegistrationInput = (EditText) findViewById(R.id.registrationPassword);

        userAuth = FirebaseAuth.getInstance();
        firebaseAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(registerActivity.this, swipeActivity.class);
                    startActivity(intent);
                    finish();
                    return;
                }
            }
        };

        finalRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userEmail = emailRegistrationInput.getText().toString();
                final String userPassword= passwordRegistrationInput.getText().toString();
                Log.d("debug", userEmail);
                Log.d("debug", userPassword);
                userAuth.createUserWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(registerActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(registerActivity.this, "Registration error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        userAuth.addAuthStateListener(firebaseAuthStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        userAuth.removeAuthStateListener(firebaseAuthStateListener);
    }
}