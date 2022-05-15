package com.dmiiy.wayapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.maps.model.LatLng;

public class createroadsplash extends AppCompatActivity {
LottieAnimationView lottie;
    LatLng mLatLng ,mLatl,mLat,mLa,mlaton,mlato,mlatono,mlatonono;
    String mDescription,mdes,mde,md,mdesco,mdescon,mdescono,mdesconon,act1,act2,act3,act4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createroadsplash);
        lottie=(LottieAnimationView) findViewById(R.id.lottiecreateroad);
        //lottie.animate().translationY(-1200).setDuration(2000).setStartDelay(0);
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

        act1=intent.getStringExtra("t1");
        act2=intent.getStringExtra("t2");
        act3=intent.getStringExtra("t3");
        act4=intent.getStringExtra("t4");

       // check1=intent.getStringExtra("check1");
        Handler handler= new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (act1 !=null){
                    Bundle args = new Bundle();
                    args.putParcelable("latLon", mLatLng);
                    args.putString("desc", mDescription);

                    args.putParcelable("latL", mLatl);
                    args.putString("de", mdes);

                    args.putParcelable("lat", mLat);
                    args.putString("d", mde);

                    args.putParcelable("la", mLa);
                    args.putString("des", md);

                    args.putParcelable("lant", mlato);
                    args.putString("desca", mdesco);

                    args.putParcelable("lanta", mlaton);
                    args.putString("descan", mdescon);

                    args.putParcelable("lantano", mlatono);
                    args.putString("descara", mdescono);
                    if (mlatonono != null){
                        args.putParcelable("lantana", mlatonono);
                        args.putString("descanra", mdesconon);
                    }
                    Intent i = new Intent(createroadsplash.this, MapsActivity.class);
                    i.putExtras(args);
                    startActivity(i);
                    finish();
                }
             if (act2 != null){
                 Bundle args = new Bundle();
                 args.putParcelable("latLon", mLatLng);
                 args.putString("desc", mDescription);

                 args.putParcelable("latL", mLatl);
                 args.putString("de", mdes);

                 args.putParcelable("lat", mLat);
                 args.putString("d", mde);

                 Intent i = new Intent(createroadsplash.this, MapsActivity.class);
                 i.putExtras(args);
                 startActivity(i);
                 finish();
             }
                if (act3 !=null) {
                    Bundle args = new Bundle();
                    args.putParcelable("latLon", mLatLng);
                    args.putString("desc", mDescription);

                    args.putParcelable("latL", mLatl);
                    args.putString("de", mdes);

                    args.putParcelable("lat", mLat);
                    args.putString("d", mde);

                    args.putParcelable("la", mLa);
                    args.putString("des", md);
                    if (mlato != null){
                        args.putParcelable("lant", mlato);
                        args.putString("desca", mdesco);
                    }
                    Intent i = new Intent(createroadsplash.this, MapsActivity.class);
                    i.putExtras(args);
                    startActivity(i);
                    finish();
                }
                if (act4 !=null) {
                    Bundle args = new Bundle();
                    args.putParcelable("latLon", mLatLng);
                    args.putString("desc", mDescription);

                    if (mLatl != null){
                        args.putParcelable("latL", mLatl);
                        args.putString("de", mdes);
                    }
                    Intent i = new Intent(createroadsplash.this, MapsActivity.class);
                    i.putExtras(args);
                    startActivity(i);
                    finish();
                }
            }
        },5000);
    }
}