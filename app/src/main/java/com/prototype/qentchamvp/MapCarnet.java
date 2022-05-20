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

public class MapCarnet extends SupportMapFragment implements OnMapReadyCallback
{
    private GoogleMap googleMap;
    private String nom;
    private double lat;
    private double lng;

    public MapCarnet(String nom, double lat, double lng)  {
        getMapAsync(this);
        
        this.nom = nom;
        this.lat = lat;
        this.lng = lng;
    }

    public MapCarnet()  {
        getMapAsync(this);

        this.nom = "Zero Island";
        this.lat = 0.0;
        this.lng = 0.0;
    }

    public void set(String nom, double lat, double lng)
    {
        this.nom = nom;
        this.lat = lat;
        this.lng = lng;
    }

    @Override
    public void onMapReady(final GoogleMap gmap) {
        this.googleMap = gmap;

        LatLng point = new LatLng(this.lat, this.lng);
        this.googleMap.addMarker(new MarkerOptions().position(point).title(this.nom));
        this.googleMap.moveCamera(CameraUpdateFactory.newLatLng(point));

        this.googleMap.animateCamera(CameraUpdateFactory.zoomTo(5));

    }

}
