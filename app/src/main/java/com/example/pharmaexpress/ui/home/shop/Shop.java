package com.example.pharmaexpress.ui.home.shop;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pharmaexpress.MainActivity3;
import com.example.pharmaexpress.R;

public class Shop extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_shop, container, false);

        // Directly start MainActivity3
        Intent intent = new Intent(getActivity(), MainActivity3.class);
        startActivity(intent);

        return root;
    }
}
