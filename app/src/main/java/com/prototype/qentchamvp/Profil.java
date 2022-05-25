package com.prototype.qentchamvp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Profil extends AppCompatActivity {
    ImageView img;
    ImageButton btnPhoto;

    SharedPreferences spParametres;
    SharedPreferences.Editor parametresEditor;

    EditText etNom, etAdr, etBio;
    TextView tvNom;

    // constante
    int SELECT_PICTURE = 200;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        // Informations générales
        this.spParametres     = getSharedPreferences("parametres", MODE_PRIVATE);
        this.parametresEditor = this.spParametres.edit();

        this.etNom = (EditText) (findViewById(R.id.editNom));
        this.etAdr = (EditText) (findViewById(R.id.editAdr));
        this.etBio = (EditText) (findViewById(R.id.editBio));
        this.tvNom = (TextView) (findViewById(R.id.tvNom));



        //Vues
        img = findViewById(R.id.photo);
        btnPhoto = findViewById(R.id.btnSelectPhoto);

        // Bouton du choix de l'image
        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choisirImage();
            }
        });


        // Initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);

        // Set carte selected
        bottomNavigationView.setSelectedItemId(R.id.action_profil);

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
                        startActivity( new Intent( getApplicationContext()
                                ,MonCarnet.class) );
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.action_profil:
                        return true;
                }
                return false;
            }
        } );
    }

    // Fonction appelée au clique du bouton image
    void choisirImage() {
        // Instance de type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        // On compare la constante et la requête
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }

    // Fonction appelé lors de la sélection de l'image de ChoisirImage
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            // Comparaison
            if (requestCode == SELECT_PICTURE) {
                // On prend l'url de l'image
                Uri imgUri = data.getData();
                if (null != imgUri) {
                    // on met à jour l'ancienne image dans le layout
                    img.setImageURI(imgUri);
                }
            }
        }
    }

    // Vérification de la saisie de l'utilisateur
    boolean validateInput() {
        if (etNom.getText().toString().equals("")) {
            etNom.setError("Entrez votre nom");
            return false;
        }

        if (etAdr.getText().toString().equals("")) {
            etAdr.setError("Entrez votre adresse courriel");
            return false;
        }

        // Format de l'email
        if (!isEmailValid(etAdr.getText().toString())) {
            etAdr.setError("Entrez une adresse courriel valide");
            return false;
        }

        return true;
    }

    // email valide
    boolean isEmailValid(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public void sauvegarder(View view) {
        if( validateInput() )
        {
            String nom = this.etNom.getText().toString();
            String adr = this.etAdr.getText().toString();
            String bio = this.etBio.getText().toString();

            tvNom.setText(nom);

            this.parametresEditor.putString("nom", nom);
            this.parametresEditor.putString("adresse", adr);
            this.parametresEditor.putString("bio", bio);

            this.parametresEditor.commit();
        }

    }

}