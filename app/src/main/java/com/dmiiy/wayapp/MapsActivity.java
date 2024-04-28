package com.dmiiy.wayapp;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CustomCap;
import com.google.android.gms.maps.model.Dash;
import com.google.android.gms.maps.model.Dot;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.JointType;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RoundCap;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,View.OnClickListener{
    private GoogleMap mMap;
    LatLng mLatLng ,mLatl,mLat,mLa,mlaton,mlato,mlatono,mlatonono;
    String mDescription,mdes,mde,md,mdesco,mdescon,mdescono,mdesconon,act;
    private ImageButton imageButton,imageView;
    private ImageView qr_scan;

    private Button btn0,btn1,btn2,btn3;

    private int ACCESS_LOCATION_REQUEST_CODE = 10001;


    private Context ctx;
FusedLocationProviderClient fusedLocationProviderClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ctx = getApplicationContext();
        fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(this);
        Intent intent = getIntent();
        mLatLng = intent.getParcelableExtra("latLon");
        mDescription = intent.getStringExtra("desc");
        mLatl= intent.getParcelableExtra("latL");
        mdes = intent.getStringExtra("de");
        mLat= intent.getParcelableExtra("lat");
        mde = intent.getStringExtra("d");
        mLa= intent.getParcelableExtra("la");
        md = intent.getStringExtra("des");
        mlato= intent.getParcelableExtra("lant");
        mdesco = intent.getStringExtra("desca");
        mlaton= intent.getParcelableExtra("lanta");
        mdescon = intent.getStringExtra("descan");
        mlatono= intent.getParcelableExtra("lantano");
        mdescono = intent.getStringExtra("descara");
        mlatonono= intent.getParcelableExtra("lantana");
        mdesconon = intent.getStringExtra("descanra");
        act=intent.getStringExtra("act");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        imageView= (ImageButton) findViewById(R.id.imageV);
        imageView.setOnClickListener(this);
        imageButton= (ImageButton) findViewById(R.id.btn_open_bottom_sheet);
        imageButton.setOnClickListener(this);

        qr_scan = findViewById(R.id.qr_button);
        qr_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MapsActivity.this, ScanqrActivity.class);
                startActivity(intent1);
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
                Intent intent= new Intent(MapsActivity.this, Profile.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_open_bottom_sheet:{
                if (act != null){
                    Intent intent= new Intent(MapsActivity.this,Trip1.class);
                    startActivity(intent);
                } else {
                    clickOpenBottomSheetFragment();
                }
                break;
            }

        }
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (mLatLng != null) {
            // Add a marker for location/description sent from MainActivity
            mMap.addMarker(new MarkerOptions().position(mLatLng).title(mDescription).icon(BitmapDescriptorFactory.fromResource(R.drawable.m1)));

        }
        if (mLatl != null) {
            // Add a marker for location/description sent from MainActivity
            mMap.addMarker(new MarkerOptions().position(mLatl).title(mdes).icon(BitmapDescriptorFactory.fromResource(R.drawable.m2)));

        }
        if (mLat != null) {
            // Add a marker for location/description sent from MainActivity
            mMap.addMarker(new MarkerOptions().position(mLat).title(mde).icon(BitmapDescriptorFactory.fromResource(R.drawable.m3)));

        }
        if (mLa != null) {
            // Add a marker for location/description sent from MainActivity
            mMap.addMarker(new MarkerOptions().position(mLa).title(md).icon(BitmapDescriptorFactory.fromResource(R.drawable.m4)));

        }
        if (mlato != null) {
            // Add a marker for location/description sent from MainActivity
            mMap.addMarker(new MarkerOptions().position(mlato).title(mdesco).icon(BitmapDescriptorFactory.fromResource(R.drawable.m5)));

        }
        if (mlaton != null) {
            // Add a marker for location/description sent from MainActivity
            mMap.addMarker(new MarkerOptions().position(mlaton).title(mdescon).icon(BitmapDescriptorFactory.fromResource(R.drawable.m6)));

        }
        if (mlatono != null) {
            // Add a marker for location/description sent from MainActivity
            mMap.addMarker(new MarkerOptions().position(mlatono).title(mdescono).icon(BitmapDescriptorFactory.fromResource(R.drawable.m7)));

        }
        if (mlatonono != null) {
            // Add a marker for location/description sent from MainActivity
            mMap.addMarker(new MarkerOptions().position(mlatonono).title(mdesconon).icon(BitmapDescriptorFactory.fromResource(R.drawable.m8)));

        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            enableUserLocation();
            //zoomToUserLocation();
            LatLng center =new LatLng(55.751135,37.617315);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(center,9));
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                //We can show user a dialog why this permission is necessary
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, ACCESS_LOCATION_REQUEST_CODE);
            } else  {
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, ACCESS_LOCATION_REQUEST_CODE);
            }

        }

        Polygon polygon1 = googleMap.addPolygon(new PolygonOptions()
                .clickable(true)
                .add(
                        new LatLng(55.8787, 37.394),
                        new LatLng(55.7687, 37.394),
                        new LatLng(55.7687,37.492 ),
                        new LatLng(55.8487,37.524 ),
                        new LatLng(55.8787,37.524 )));
        // Store a data object with the polygon, used here to indicate an arbitrary type.
        polygon1.setTag("alpha");
        // [END maps_poly_activity_add_polygon]
        // Style the polygon.
        stylePolygon(polygon1);


        Polygon polygon2 = googleMap.addPolygon(new PolygonOptions()
                .clickable(true)
                .add(
                        new LatLng(55.8787, 37.524),
                        new LatLng(55.8487, 37.524),
                        new LatLng(55.8187, 37.594),
                        new LatLng(55.7987, 37.644),
                        new LatLng(55.8787, 37.724)));
        polygon2.setTag("beta");
        stylePolygon(polygon2);

        Polygon polygon3 = googleMap.addPolygon(new PolygonOptions()
                .clickable(true)
                .add(
                        new LatLng(55.8787, 37.724),
                        new LatLng(55.7987, 37.644),
                        new LatLng(55.7287, 37.724),
                        new LatLng(55.8187, 37.8270)));
        polygon3.setTag("three");
        stylePolygon(polygon3);

        Polygon polygon4 = googleMap.addPolygon(new PolygonOptions()
                .clickable(true)
                .add(
                        new LatLng(55.8187, 37.827),
                        new LatLng(55.7287, 37.724),
                        new LatLng(55.6687, 37.827)));
        polygon4.setTag("four");
        stylePolygon(polygon4);

        Polygon polygon5 = googleMap.addPolygon(new PolygonOptions()
                .clickable(true)
                .add(
                        new LatLng(55.6687, 37.827),
                        new LatLng(55.7287, 37.724),
                        new LatLng(55.6187, 37.644)));
        polygon5.setTag("five");
        stylePolygon(polygon5);

        Polygon polygon6 = googleMap.addPolygon(new PolygonOptions()
                .clickable(true)
                .add(
                        new LatLng(55.6187, 37.644),
                        new LatLng(55.7187, 37.544),
                        new LatLng(55.6187, 37.514)));
        polygon6.setTag("six");
        stylePolygon(polygon6);

        Polygon polygon7 = googleMap.addPolygon(new PolygonOptions()
                .clickable(true)
                .add(
                        new LatLng(55.6187, 37.514),
                        new LatLng(55.7187, 37.544),
                        new LatLng(55.7687, 37.492),
                        new LatLng(55.7687, 37.394)));
        polygon7.setTag("seven");
        stylePolygon(polygon7);

        Polygon polygon8 = googleMap.addPolygon(new PolygonOptions()
                .clickable(true)
                .add(
                        new LatLng(55.7687, 37.492),
                        new LatLng(55.8487, 37.524),
                        new LatLng(55.8187, 37.594),
                        new LatLng(55.7987, 37.644),
                        new LatLng(55.7187, 37.544)));
        polygon8.setTag("eight");
        stylePolygon(polygon8);

        Polygon polygon9 = googleMap.addPolygon(new PolygonOptions()
                .clickable(true)
                .add(
                        new LatLng(55.7987, 37.644),
                        new LatLng(55.7287, 37.7240),
                        new LatLng(55.6187, 37.644),
                        new LatLng(55.7987, 37.644)));
        polygon9.setTag("nine");
        stylePolygon(polygon9);

        Polygon polygon10 = googleMap.addPolygon(new PolygonOptions()
                .clickable(true)
                .add(
                        new LatLng(55.7987, 37.644),
                        new LatLng(55.6187, 37.644),
                        new LatLng(55.7187,  37.5440),
                        new LatLng(55.7987, 37.644)));
        polygon10.setTag("ten");
        stylePolygon(polygon10);



    }

    private static final int COLOR_BLACK_ARGB = 0xff000000;
    private static final int POLYLINE_STROKE_WIDTH_PX = 12;


    private static final int PATTERN_GAP_LENGTH_PX = 20;
    private static final PatternItem DOT = new Dot();
    private static final PatternItem GAP = new Gap(PATTERN_GAP_LENGTH_PX);

    // Create a stroke pattern of a gap followed by a dot.
    private static final List<PatternItem> PATTERN_POLYLINE_DOTTED = Arrays.asList(GAP, DOT);


    private static final int COLOR_WHITE_ARGB = 0xffffffff;
    private static final int COLOR_DARK_GREEN_ARGB = 0xff388E3C;
    private static final int COLOR_LIGHT_GREEN_ARGB = 0xff81C784;
    private static final int COLOR_DARK_ORANGE_ARGB = 0xffF57F17;
    private static final int COLOR_LIGHT_ORANGE_ARGB = 0xffF9A825;

    private static final int POLYGON_STROKE_WIDTH_PX = 8;
    private static final int PATTERN_DASH_LENGTH_PX = 20;
    private static final PatternItem DASH = new Dash(PATTERN_DASH_LENGTH_PX);

    // Create a stroke pattern of a gap followed by a dash.
    private static final List<PatternItem> PATTERN_POLYGON_ALPHA = Arrays.asList(GAP, DASH);

    // Create a stroke pattern of a dot followed by a gap, a dash, and another gap.
    private static final List<PatternItem> PATTERN_POLYGON_BETA =
            Arrays.asList(DOT, GAP, DASH, GAP);

    private void stylePolygon(Polygon polygon) {
        String type = "";
        // Get the data object stored with the polygon.
        if (polygon.getTag() != null) {
            type = polygon.getTag().toString();
        }

        List<PatternItem> pattern = null;
        int strokeColor = COLOR_BLACK_ARGB;
        int fillColor = COLOR_WHITE_ARGB;

        switch (type) {
            // If no type is given, allow the API to use the default.
            case "alpha": {
                // Apply a stroke pattern to render a dashed line, and define colors.
                pattern = PATTERN_POLYGON_ALPHA;
                strokeColor = COLOR_DARK_GREEN_ARGB;
                fillColor = Color.argb(100, 255, 0, 0);
                break;
            }
            case "beta": {
                // Apply a stroke pattern to render a line of dots and dashes, and define colors.
                pattern = PATTERN_POLYGON_ALPHA;
                strokeColor = COLOR_DARK_GREEN_ARGB;
                fillColor = Color.argb(100, 0, 255, 0);
                break;
            }
            case "three": {
                // Apply a stroke pattern to render a line of dots and dashes, and define colors.
                pattern = PATTERN_POLYGON_ALPHA;
                strokeColor = COLOR_DARK_GREEN_ARGB;
                fillColor = Color.argb(100, 255, 255, 0);
                break;
            }
            case "four": {
                // Apply a stroke pattern to render a line of dots and dashes, and define colors.
                pattern = PATTERN_POLYGON_ALPHA;
                strokeColor = COLOR_DARK_GREEN_ARGB;
                fillColor = Color.argb(100, 255, 255, 0);
                break;
            }
            case "five": {
                // Apply a stroke pattern to render a line of dots and dashes, and define colors.
                pattern = PATTERN_POLYGON_ALPHA;
                strokeColor = COLOR_DARK_GREEN_ARGB;
                fillColor = Color.argb(100, 255, 0, 0);
                break;
            }
            case "six": {
                // Apply a stroke pattern to render a line of dots and dashes, and define colors.
                pattern = PATTERN_POLYGON_ALPHA;
                strokeColor = COLOR_DARK_GREEN_ARGB;
                fillColor = Color.argb(100, 0, 255, 0);
                break;
            }
            case "seven": {
                // Apply a stroke pattern to render a line of dots and dashes, and define colors.
                pattern = PATTERN_POLYGON_ALPHA;
                strokeColor = COLOR_DARK_GREEN_ARGB;
                fillColor = Color.argb(100, 180, 180, 180);
                break;
            }
            case "eight": {
                // Apply a stroke pattern to render a line of dots and dashes, and define colors.
                pattern = PATTERN_POLYGON_ALPHA;
                strokeColor = COLOR_DARK_GREEN_ARGB;
                fillColor = Color.argb(100, 0, 255, 0);
                break;
            }
            case "nine": {
                // Apply a stroke pattern to render a line of dots and dashes, and define colors.
                pattern = PATTERN_POLYGON_ALPHA;
                strokeColor = COLOR_DARK_GREEN_ARGB;
                fillColor = Color.argb(100, 0, 255, 0);
                break;
            }
            case "ten": {
                // Apply a stroke pattern to render a line of dots and dashes, and define colors.
                pattern = PATTERN_POLYGON_ALPHA;
                strokeColor = COLOR_DARK_GREEN_ARGB;
                fillColor = Color.argb(100, 255, 255, 0);
                break;
            }
        }

        polygon.setStrokePattern(pattern);
        polygon.setStrokeWidth(POLYGON_STROKE_WIDTH_PX);
        polygon.setStrokeColor(strokeColor);
        polygon.setFillColor(fillColor);
    }

    private void enableUserLocation() {
        mMap.setMyLocationEnabled(true);
    }
    private void zoomToUserLocation(){
    Task<Location> locationTask= fusedLocationProviderClient.getLastLocation();
    locationTask.addOnSuccessListener(new OnSuccessListener<Location>() {
        @Override
        public void onSuccess(Location location) {
          LatLng latLng= new LatLng(location.getLatitude(),location.getLongitude());
          mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,20));

        }
    });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == ACCESS_LOCATION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                enableUserLocation();
                //zoomToUserLocation();
                LatLng center =new LatLng(55.751135,37.617315);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(center,9));
            } else {
                //We can show a dialog that permission is not granted...
            }
        }
    }
}