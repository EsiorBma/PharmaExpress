package com.example.pharmaexpress;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.pharmaexpress.ui.home.HomeFragment;
import com.example.pharmaexpress.ui.home.account.Account;
import com.example.pharmaexpress.ui.home.location.location;
import com.example.pharmaexpress.ui.home.shop.Shop;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.pharmaexpress.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Vérifiez si savedInstanceState est null pour éviter les fragments superposés
        if (savedInstanceState == null) {
            // Créez une instance de HomeFragment
            Fragment homeFragment = new HomeFragment();
            // Obtenez le FragmentManager
            FragmentManager fragmentManager = getSupportFragmentManager();
            // Commencez une nouvelle transaction de fragment
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            // Remplacez le conteneur par HomeFragment
            fragmentTransaction.replace(R.id.fragment_container, homeFragment);
            // Engagez la transaction
            fragmentTransaction.commit();
        }

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        selectedFragment = new HomeFragment();
                        break;
                    case R.id.navigation_location:
                        selectedFragment = new location();
                        break;
                    case R.id.navigation_shop:
                        selectedFragment = new Shop();
                        break;
                    case R.id.navigation_account:
                        selectedFragment = new Account();
                        break;
                }
                if (selectedFragment != null) {
                    loadFragment(selectedFragment);
                }
                return true;
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        // Remplacez le fragment dans le conteneur
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

}