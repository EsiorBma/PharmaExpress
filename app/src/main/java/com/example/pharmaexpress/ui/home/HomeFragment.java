package com.example.pharmaexpress.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.pharmaexpress.R;
import com.example.pharmaexpress.databinding.FragmentHomeBinding;
import com.example.pharmaexpress.medicaments.advacare;
import com.example.pharmaexpress.medicaments.broncalene;
import com.example.pharmaexpress.medicaments.fever;
import com.example.pharmaexpress.medicaments.iprafeine;
import com.example.pharmaexpress.medicaments.para_sandol;
import com.example.pharmaexpress.medicaments.paracare;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    public HomeFragment() {
        // Required empty public constructor
    }

    private HomeViewModel homeViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        root.findViewById(R.id.para_sandol_card).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), para_sandol.class));
            }
        });

        root.findViewById(R.id.advacare_card).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), advacare.class));
            }
        });
        root.findViewById(R.id.fever_card).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), fever.class));
            }
        });
        root.findViewById(R.id.paracare_card).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), paracare.class));
            }
        });

        root.findViewById(R.id.bronc_card).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), broncalene.class));
            }
        });
        root.findViewById(R.id.ipraf_card).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), iprafeine.class));
            }
        });
        final TextView textViewWelcome = root.findViewById(R.id.text_welcome);
        final TextView textViewNotifications = root.findViewById(R.id.text_notifications);

        homeViewModel.getWelcomeMessage().observe(getViewLifecycleOwner(), textViewWelcome::setText);
        homeViewModel.getNotifications().observe(getViewLifecycleOwner(), textViewNotifications::setText);

        return root;
    }


}