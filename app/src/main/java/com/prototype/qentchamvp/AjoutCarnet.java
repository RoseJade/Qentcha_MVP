package com.prototype.qentchamvp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class AjoutCarnet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_carnet);

        //bouton ajout point
        Button btnAjoutPoint = findViewById(R.id.btnAjoutPoint);
        btnAjoutPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenNewActivity();
            }
        });

        // bouton previsualiser
        Button btnPrevi = findViewById(R.id.btnPrevi);
        btnPrevi.setOnClickListener((View.OnClickListener) btnPrevi);

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);

        // Set carte selected
        bottomNavigationView.setSelectedItemId(R.id.action_ajoutcarnet);

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

    public void OpenNewActivity() {
        Intent intent = new Intent(this, AjoutPoint.class);
        startActivity(intent);
    }
}