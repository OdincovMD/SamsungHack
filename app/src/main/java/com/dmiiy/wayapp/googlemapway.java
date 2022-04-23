package com.dmiiy.wayapp;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.ButtCap;
import com.google.android.gms.maps.model.JointType;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.dmiiy.wayapp.databinding.ActivityGooglemapwayBinding;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class googlemapway extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityGooglemapwayBinding binding;
private Apiinterface apiinterface;
private List<LatLng> polylineslist;
private PolylineOptions polylineOptions;
private LatLng origion,dest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityGooglemapwayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Retrofit retrofit=new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("https://maps.googleapis.com")
                .build();
        apiinterface=retrofit.create(Apiinterface.class);

    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setTrafficEnabled(true);
        getDirection("55.751244"+","+"37.618423","59.937500"+","+"30.308611");
        origion=new LatLng(55.751244,37.618423);
        dest=new LatLng(59.937500,30.308611);
    }
    private void getDirection(String origin, String destination){
        apiinterface.getDirection("walk","less walk",origin,destination,
                getString(R.string.api_key)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Result result) {
                        polylineslist = new ArrayList<>();
                        List<Route>routeList=result.getRoutes();
                        for (Route route:routeList){
                            String polyline= route.getOverviewPolyline().getPoints();
                            polylineslist.addAll(decodePoly(polyline));
                        }
                        polylineOptions= new PolylineOptions();
                        polylineOptions.color(ContextCompat.getColor(getApplicationContext(),R.color.way));
                        polylineOptions.width(8);
                        polylineOptions.startCap(new ButtCap());
                        polylineOptions.jointType(JointType.ROUND);
                        polylineOptions.addAll(polylineslist);
                        mMap.addPolyline(polylineOptions);
                        LatLngBounds.Builder builder= new LatLngBounds.Builder();
                        builder.include(origion);
                        builder.include(dest);
                        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(),100));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }
    private List<LatLng> decodePoly(String encoded){
        List<LatLng>poly = new ArrayList<>();
        int index = 0,len = encoded.length();
        int lat=0,lng=0;
        while (index<len){
            int b,shift=0,result=0;
            do{
                b=encoded.charAt(index++)-63;
                result |=(b & 0x1f)<<shift;
                shift+=5;
            }
            while (b>=0x20);
            int dlat=((result & 1)!=0 ? ~(result>>1):(result>>1));
            lat+=dlat;
            shift=0;
            result=0;
            do{
                b=encoded.charAt(index++)-63;
                result |=(b & 0x1f)<<shift;
                shift+=5;
            }
            while (b>=0x20);
            int dlng=((result & 1)!=0 ? ~(result>>1):(result>>1));
            lng+=dlng;

            LatLng p = new LatLng((((double) lat/1E5)),(((double) lng/1E5)));
            poly.add(p);
        }
        return poly;
    }

}
