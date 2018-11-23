package com.example.zahut.easyrecipe;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loginActivity extends AppCompatActivity {

    private Button finalLoginBtn;
    private EditText emailLoginInput, passwordLoginInput;

    private FirebaseAuth userAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        finalLoginBtn = (Button) findViewById(R.id.finalLoginBtn);
        emailLoginInput = (EditText) findViewById(R.id.loginEmail);
        passwordLoginInput = (EditText) findViewById(R.id.loginPassword);

        userAuth = FirebaseAuth.getInstance();
        firebaseAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(loginActivity.this, swipeActivity.class);
                    startActivity(intent);
                    finish();
                    return;
                }
            }
        };

        finalLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userEmail = emailLoginInput.getText().toString();
                final String userPassword= passwordLoginInput.getText().toString();
                userAuth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(loginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(loginActivity.this, "Registration error", Toast.LENGTH_SHORT).show();
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