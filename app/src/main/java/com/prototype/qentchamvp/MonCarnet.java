package com.prototype.qentchamvp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MonCarnet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mon_carnet);

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

        // Ajout des cartes

        FragmentManager fragmentManager = this.getSupportFragmentManager();
        MapCarnet m1 = (MapCarnet) fragmentManager.findFragmentById(R.id.map1);
        m1.set("Centre Ancien de La Paz",-16.5,-68.15);

        MapCarnet m2 = (MapCarnet) fragmentManager.findFragmentById(R.id.map2);
        m2.set("Randonnée dans l'Altiplano",-16.00358,-69.65332);

        MapCarnet m3 = (MapCarnet) fragmentManager.findFragmentById(R.id.map3);
        m3.set("Centre ancien de Phnom Penh",11.5448729,104.8921668);

        MapCarnet m4 = (MapCarnet) fragmentManager.findFragmentById(R.id.map4);
        m4.set("Centre ancien de Phnom Penh",11.5448729,104.8921668);
    }
}