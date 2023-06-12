package com.example.appreciclar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.checkerframework.checker.nullness.qual.NonNull;

public class Ubicacion extends AppCompatActivity {

    GoogleMap mMap;
    MapView mapView;
    Double latitud, longitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubicacion);

        Intent intent = getIntent();
        latitud = intent.getDoubleExtra("latitud",0.0);
        longitud = intent.getDoubleExtra("longitud",0.0);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync((OnMapReadyCallback) this);


    }

    public void onMapReady(@NonNull GoogleMap googleMap){
        mMap = googleMap;


        LatLng lugar = new LatLng(latitud, longitud);
        mMap.addMarker(new MarkerOptions().position(lugar).title("lugar"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(lugar));


    }
}