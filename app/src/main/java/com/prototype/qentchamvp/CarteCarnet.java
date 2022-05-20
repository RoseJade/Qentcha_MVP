package com.prototype.qentchamvp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;

// Google Maps
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;

// Services de localisation
import android.location.LocationManager;
import android.os.Bundle;
import android.content.Context;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class CarteCarnet extends AppCompatActivity implements LocationListener, GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener, View.OnClickListener, OnMapReadyCallback {

    private MapAccueil carte;
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carte_carnet);

        // ===> Carte <===
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        this.carte = (MapAccueil) fragmentManager.findFragmentById(R.id.mapHome);

        // ===> Localisation <===
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // SupportMapFragment nécéssaire pour le service de localisation
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapHome);
        mapFragment.getMapAsync(this);

        // ===> Barre de Navigation <===
        // Initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);

        // Set carte selected
        bottomNavigationView.setSelectedItemId(R.id.action_moncarnet);

        // Perform ItemSelectedListener
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch ( menuItem.getItemId()) {
                    case R.id.action_carte:
                        startActivity( new Intent( getApplicationContext()
                                ,MainActivity.class) );
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.action_ajoutcarnet:
                        startActivity( new Intent( getApplicationContext()
                                ,AjoutCarnet.class) );
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.action_moncarnet:
                        return true;

                    case R.id.action_profil:
                        startActivity( new Intent( getApplicationContext()
                                ,Profil.class) );
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        } );

        findViewById(R.id.btnGlobe).setOnClickListener(this);
        findViewById(R.id.btnListe).setOnClickListener(this);
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
    }

    // Ajout de la surcouche localisation à la carte
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
    public void onClick(View view) {
        if (view.getId() == R.id.btnGlobe)
        {
            this.map.animateCamera(CameraUpdateFactory.zoomTo(1));
        }

        if (view.getId() == R.id.btnListe)
        {
            Intent intent = new Intent(this, MonCarnet.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}