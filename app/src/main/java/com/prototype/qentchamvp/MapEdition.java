package com.prototype.qentchamvp;

// Services de localisation
import android.location.LocationManager;
import android.location.LocationListener;
import android.os.Bundle;
import android.content.Context;

// Carte Google Maps
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapEdition extends SupportMapFragment implements OnMapReadyCallback
{
    private GoogleMap googleMap;

    public MapEdition()  {
        getMapAsync(this);
    }

    @Override
    public void onMapReady(final GoogleMap gmap) {
        this.googleMap = gmap;

        LatLng lehavre = new LatLng(49.5, 0.13333);
        this.googleMap.addMarker(new MarkerOptions().position(lehavre).title("Le Havre"));
        this.googleMap.moveCamera(CameraUpdateFactory.newLatLng(lehavre));

        /*

        this.googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title(latLng.latitude + " : " + latLng.longitude);

                googleMap.clear();

                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));

                googleMap.addMarker(markerOptions);
            }
        });


         */

    }

}
