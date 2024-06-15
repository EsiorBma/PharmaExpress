package com.example.pharmaexpress;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {

    private EditText email_log, password_log;
    String email_input, password_input;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private FirebaseAuth mAuth;

    private ProgressBar progressBar;

    @Override
    public void onStart() {
        super.onStart();
        initializeFirebaseAuth();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Initialize Firebase Auth
        initializeFirebaseAuth();

        //récupérer les champs du formulaire via leur id

        email_log = findViewById(R.id.mail_id);
        password_log = findViewById(R.id.password_id);
        progressBar = findViewById(R.id.progressbar);

        // Action sous le bouton sign in
        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar.setVisibility(View.VISIBLE);
                // Récupérer les données saisi

                email_input = email_log.getText().toString();
                password_input = password_log.getText().toString();

                // Vérifier si tous les champs ont été remplis
                if(email_input.isEmpty() || password_input.isEmpty()){
                    Toast.makeText(Login.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                }else{

                    mAuth.signInWithEmailAndPassword(email_input, password_input)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {
                                        Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();

                                        onStart();

                                    } else {
                                        // If sign in fails, display a message to the user.

                                        Toast.makeText(Login.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });


                }
            }
        });

    }
    private void initializeFirebaseAuth() {
        if (mAuth == null) {
            mAuth = FirebaseAuth.getInstance();
        }
    }
}