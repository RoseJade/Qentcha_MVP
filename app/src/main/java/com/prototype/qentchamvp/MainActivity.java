package com.prototype.qentchamvp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

// Google Maps
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener;
import com.google.android.gms.maps.GoogleMap.OnMyLocationClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

// Services de localisation
import android.location.*;
import android.location.Location;
import android.os.Bundle;
import android.content.Context;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

// Barre de Navigation
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity
        implements LocationListener,
        OnMyLocationButtonClickListener,
        OnMyLocationClickListener,
        OnMapReadyCallback,
        View.OnClickListener,
        ActivityCompat.OnRequestPermissionsResultCallback {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    private MapAccueil carte;
    private GoogleMap map;
    private FusedLocationProviderClient fusedLocationClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ===> Carte <===

        FragmentManager fragmentManager = this.getSupportFragmentManager();
        this.carte = (MapAccueil) fragmentManager.findFragmentById(R.id.mapHome);

        // SupportMapFragment nécéssaire pour le service de localisation
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapHome);
        mapFragment.getMapAsync(this);

        // ===> Localisation <===

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        // ===> Bouton vue globe <===

        findViewById(R.id.btnGlobe).setOnClickListener(this);

        // ===> Barre de Navigation <===

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);

        // Set carte selected
        bottomNavigationView.setSelectedItemId(R.id.action_carte);

        // Perform ItemSelectedListener
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_carte:
                        return true;

                    case R.id.action_ajoutcarnet:
                        startActivity(new Intent(getApplicationContext()
                                , AjoutCarnet.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.action_moncarnet:
                        startActivity(new Intent(getApplicationContext()
                                , MonCarnet.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.action_profil:
                        startActivity(new Intent(getApplicationContext()
                                , Profil.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
    }

    // Ajout de la surcouche localisation à la carte
    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.map = googleMap;
        this.map.setOnMyLocationButtonClickListener(this);
        this.map.setOnMyLocationClickListener(this);

        // Verification de l'autorisation de récupérer la localisation
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        this.map.setMyLocationEnabled(true);
        //Location loc = fusedLocationClient.getLastLocation().getResult();

        //map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(loc.getLatitude(), loc.getLongitude()), 10));
    }

    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {

    }

    public void onClick(View view)
    {
        if (view.getId() == R.id.btnGlobe)
        {
            this.map.animateCamera(CameraUpdateFactory.zoomTo(1));
        }
    }


}

