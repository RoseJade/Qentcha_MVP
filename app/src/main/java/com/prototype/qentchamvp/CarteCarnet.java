package com.prototype.qentchamvp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Switch;

// Google Maps
import androidx.fragment.app.FragmentManager;

// Services de localisation
import android.location.LocationManager;
import android.os.Bundle;
import android.content.Context;

import com.google.android.gms.location.LocationListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class CarteCarnet extends AppCompatActivity implements LocationListener {

    private MapAccueil carte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carte_carnet);

        // ===> Carte <===
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        this.carte = (MapAccueil) fragmentManager.findFragmentById(R.id.mapHome);

        // ===> Localisation <===
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);


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
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
    }
}