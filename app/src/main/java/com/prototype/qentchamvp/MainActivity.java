package com.prototype.qentchamvp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Switch;

import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    private MapAccueil carte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = this.getSupportFragmentManager();
        this.carte = (MapAccueil) fragmentManager.findFragmentById(R.id.mapHome);

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);

        // Set carte selected
        bottomNavigationView.setSelectedItemId(R.id.action_carte);

        // Perform ItemSelectedListener
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch ( menuItem.getItemId()) {
                    case R.id.action_carte:
                        return true;

                    case R.id.action_ajoutcarnet:
                        startActivity( new Intent( getApplicationContext()
                                ,AjoutCarnet.class) );
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.action_moncarnet:
                        startActivity( new Intent( getApplicationContext()
                                ,MonCarnet.class) );
                        overridePendingTransition(0,0);
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
}

