package com.example.pharmaexpress;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;

public class Registration extends AppCompatActivity {

    private EditText firstname_input, surname_input, email_input, location_input, password_input;
    private String firstname, surname, email, location, password;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private DatabaseHelper dbHelper;

    @Override
    public void onStart() {
        super.onStart();
        initializeFirebaseAuth();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            startActivity(new Intent(Registration.this, MainActivity.class));
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initializeFirebaseAuth();

        // Initialize DatabaseHelper
        dbHelper = new DatabaseHelper(this);

        // Recupérer les composants via leurs id
        firstname_input = findViewById(R.id.edit_firtst_name);
        surname_input = findViewById(R.id.edit_surname);
        email_input = findViewById(R.id.edit_email);
        location_input = findViewById(R.id.edit_location);
        password_input = findViewById(R.id.edit_password);

        // composant progressBar
        progressBar = findViewById(R.id.progressbar);

        // Action sous le bouton register
        findViewById(R.id.btn_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void initializeFirebaseAuth() {
        if (mAuth == null) {
            mAuth = FirebaseAuth.getInstance();
        }
    }

    private void registerUser() {
        progressBar.setVisibility(View.VISIBLE);
        // Récupérer les données dans les champs de texte
        firstname = firstname_input.getText().toString();
        surname = surname_input.getText().toString();
        email = email_input.getText().toString();
        location = location_input.getText().toString();
        password = password_input.getText().toString();

        // Vérifier si les champs sont tous remplis
        if (firstname.isEmpty() || surname.isEmpty() || email.isEmpty() || location.isEmpty() || password.isEmpty()) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(Registration.this, "Informations incomplètes. Tous les champs sont obligatoires", Toast.LENGTH_SHORT).show();
        } else {
            // Instancier la classe utilisateur
            Utilisateur utilisateur = new Utilisateur(surname, firstname, email, password, location);

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressBar.setVisibility(View.GONE);
                            if (task.isSuccessful()) {
                                // Le compte a été créé
                                boolean isInserted = dbHelper.addUser(firstname, surname, email, password, location);
                                if (isInserted) {
                                    Toast.makeText(Registration.this, "Account created and saved locally", Toast.LENGTH_SHORT).show();
                                    onStart();
                                } else {
                                    Toast.makeText(Registration.this, "Failed to save user data locally", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(Registration.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}
