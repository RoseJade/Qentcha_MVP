package com.prototype.qentchamvp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
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

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Profil extends AppCompatActivity {
    ImageView img;
    ImageButton btnPhoto;
    Button btnAbo;

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

        // Récupération des informations sauvegardées
        this.etNom.setText( this.spParametres.getString("nom", "" ) );
        this.tvNom.setText( this.spParametres.getString("nom", "" ) );
        this.etAdr.setText( this.spParametres.getString("adresse", "" ) );
        this.etBio.setText( this.spParametres.getString("bio", "" ) );


        //Vues pour l'image, bouton photo et bouton Abonnement
        img = findViewById(R.id.photo);
        btnPhoto = findViewById(R.id.btnSelectPhoto);

        btnAbo = (Button) findViewById(R.id.btn_abo);

        // Récupère l'image
        String encodedImg = this.spParametres.getString("image", "");
        if ( !encodedImg.equals("")  )
        {
            byte[] b = Base64.decode(encodedImg, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
            img.setImageBitmap(bitmap);
        }

        // Bouton pour choisir l'image
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
                this.imageSauvegarde(data);
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

    // Méthode qui permet de récupérer les informations de l'image
    public void imageSauvegarde( Intent data)
    {
        InputStream stream = null;
        try {
            stream = getContentResolver().openInputStream(data.getData());

            Bitmap realImage = BitmapFactory.decodeStream(stream);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            realImage.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] b = baos.toByteArray();

            String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
            this.img.setImageBitmap(bitmap);

            this.parametresEditor.putString("image", encodedImage);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void abonnement( View v)
    {
        startActivity(new Intent(this, Abonnement.class));
    }

}