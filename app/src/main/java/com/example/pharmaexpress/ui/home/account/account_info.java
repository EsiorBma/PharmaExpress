package com.example.pharmaexpress.ui.home.account;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.database.Cursor;
import android.view.View;
import android.widget.EditText;

import com.example.pharmaexpress.DatabaseHelper;
import com.example.pharmaexpress.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class account_info extends AppCompatActivity {

    private EditText firstName_input, name_input, email_input, dateNais_input, phone_input;
    private FirebaseAuth auth;
    private FirebaseUser user;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        auth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_info);

        dbHelper = new DatabaseHelper(this);

        firstName_input = findViewById(R.id.Input_firtsName);
        name_input = findViewById(R.id.Input_Name);
        email_input = findViewById(R.id.Input_Mail);
        dateNais_input = findViewById(R.id.Input_Naissance);
        phone_input = findViewById(R.id.Input_Phone);

        user = auth.getCurrentUser();

        if (user != null) {
            loadUserData(user.getEmail());
        }

        findViewById(R.id.info_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void loadUserData(String email) {
        Cursor cursor = dbHelper.getUserData(email);
        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range") String firstName = cursor.getString(cursor.getColumnIndex("prenom"));
            @SuppressLint("Range") String surname = cursor.getString(cursor.getColumnIndex("nom"));
            @SuppressLint("Range") String userEmail = cursor.getString(cursor.getColumnIndex("email"));
            @SuppressLint("Range") String location = cursor.getString(cursor.getColumnIndex("adresse"));
            // Assuming you have columns for date of birth and phone in your table, else remove these lines
           // String dateOfBirth = cursor.getString(cursor.getColumnIndex("dateNais"));
         //   String phone = cursor.getString(cursor.getColumnIndex("phone"));

            firstName_input.setText(firstName);
            name_input.setText(surname);
            email_input.setText(userEmail);
           // dateNais_input.setText(dateOfBirth); // If not available, remove this line
        //    phone_input.setText(phone); // If not available, remove this line
        }
        if (cursor != null) {
            cursor.close();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbHelper.close();
    }
}
