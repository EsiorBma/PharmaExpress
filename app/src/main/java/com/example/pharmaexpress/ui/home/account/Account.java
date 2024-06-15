package com.example.pharmaexpress.ui.home.account;

import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pharmaexpress.DatabaseHelper;
import com.example.pharmaexpress.Login;
import com.example.pharmaexpress.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Account extends Fragment {

    private FirebaseAuth auth;
    private FirebaseUser user;
    private DatabaseHelper dbHelper;

    private TextView usernameInitial;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_account, container, false);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        dbHelper = new DatabaseHelper(getContext());

        usernameInitial = root.findViewById(R.id.username_initial);

        if (user != null) {
            loadUserInitials(user.getEmail());
        }

        root.findViewById(R.id.acc_commande).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), account_command.class));
            }
        });

        root.findViewById(R.id.acc_informations).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), account_info.class));
            }
        });

        root.findViewById(R.id.acc_address).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), account_address.class));
            }
        });

        root.findViewById(R.id.acc_cards).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), account_cards.class));
            }
        });

        root.findViewById(R.id.acc_logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getActivity(), Login.class));
                //System.exit(0);
            }
        });

        return root;
    }

    private void loadUserInitials(String email) {
        Cursor cursor = dbHelper.getUserData(email);
        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range") String firstName = cursor.getString(cursor.getColumnIndex("prenom"));
            @SuppressLint("Range") String surname = cursor.getString(cursor.getColumnIndex("nom"));

            String initials = "";
            if (firstName != null && !firstName.isEmpty()) {
                initials += firstName.charAt(0);
            }
            if (surname != null && !surname.isEmpty()) {
                initials += surname.charAt(0);
            }

            usernameInitial.setText(initials.toUpperCase());
        }
        if (cursor != null) {
            cursor.close();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dbHelper.close();
    }
}
