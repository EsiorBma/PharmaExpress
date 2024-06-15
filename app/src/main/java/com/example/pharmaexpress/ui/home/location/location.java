package com.example.pharmaexpress.ui.home.location;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.pharmaexpress.Pharmacie;
import com.example.pharmaexpress.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;

public class location extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final String TAG = "LocationFragment";
    private LocationRequest mLocationRequest;
    private FusedLocationProviderClient fusedLocationClient;
    private List<Pharmacie> pharmaciesList = new ArrayList<>();
    private ListView pharmacyListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView called");
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_location, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.mapView);
        mapFragment.getMapAsync(this);

        // Initialize the pharmacy list
        initPharmaciesList();

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());

        // Initialize the ListView
        pharmacyListView = rootView.findViewById(R.id.pharmacy_list);
        ArrayAdapter<Pharmacie> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, pharmaciesList);
        pharmacyListView.setAdapter(adapter);

        // Set a click listener for list items
        pharmacyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Pharmacie selectedPharmacy = pharmaciesList.get(position);
                // Handle the click event, e.g., show the pharmacy details or open its online shop
                Toast.makeText(getActivity(), "Selected: " + selectedPharmacy.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }

    private void initPharmaciesList() {
        // Add pharmacies here, for example:
        pharmaciesList.add(new Pharmacie("Pharmacie Centrale", 6.131935, 1.222782));
        pharmaciesList.add(new Pharmacie("Pharmacie de la Plage", 6.128281, 1.251601));
        // Add more pharmacies as needed
    }

    @RequiresApi(api = Build.VERSION_CODES.S)
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d(TAG, "onMapReady called");
        mMap = googleMap;

        // Check for location permission
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }

        // Enable the My Location layer
        mMap.setMyLocationEnabled(true);

        // Create a location request
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(10000); // Update location every 10 seconds

        // Get the current location
        fusedLocationClient.getLastLocation().addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 19));

                    // Add markers for each pharmacy
                    for (Pharmacie pharmacy : pharmaciesList) {
                        LatLng pharmacyLocation = new LatLng(pharmacy.getLatitude(), pharmacy.getLongitude());
                        mMap.addMarker(new MarkerOptions().position(pharmacyLocation).title(pharmacy.getName()));
                    }
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult called");
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    mMap.setMyLocationEnabled(true);

                    // Get the current location
                    fusedLocationClient.getLastLocation().addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {
                                LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15));

                                // Add markers for each pharmacy
                                for (Pharmacie pharmacy : pharmaciesList) {
                                    LatLng pharmacyLocation = new LatLng(pharmacy.getLatitude(), pharmacy.getLongitude());
                                    mMap.addMarker(new MarkerOptions().position(pharmacyLocation).title(pharmacy.getName()));
                                }
                            }
                        }
                    });
                }
            }
        }
    }
}
