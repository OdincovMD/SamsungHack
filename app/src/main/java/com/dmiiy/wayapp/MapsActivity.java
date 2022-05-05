package com.dmiiy.wayapp;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.dmiiy.wayapp.databinding.ActivityMapsBinding;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,View.OnClickListener{
    private ImageView imageView;
    private GoogleMap mMap;
   // private ActivityMapsBinding binding;
    private ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        //binding = ActivityMapsBinding.inflate(getLayoutInflater());
        //setContentView(binding.getRoot());
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        imageView = (ImageView) findViewById(R.id.imageV);
        imageView.setOnClickListener(this);
        imageButton= (ImageButton) findViewById(R.id.btn_open_bottom_sheet);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickOpenBottomSheetFragment();
            }
        });
    }

    private void clickOpenBottomSheetFragment() {
        MyBottomSheetFragment myBottomSheetFragment=new MyBottomSheetFragment();
        myBottomSheetFragment.show(getSupportFragmentManager(),myBottomSheetFragment.getTag());
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imageV:{
                Intent intent= new Intent(MapsActivity.this,WayAppmap.class);
                startActivity(intent);
                break;
            }

        }
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

}