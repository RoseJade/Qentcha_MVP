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

public class MapAccueil extends SupportMapFragment implements OnMapReadyCallback
{
    private GoogleMap googleMap;

    public MapAccueil()  {
        getMapAsync(this);
    }

    @Override
    public void onMapReady(final GoogleMap gmap) {
        this.googleMap = gmap;

    }

}
