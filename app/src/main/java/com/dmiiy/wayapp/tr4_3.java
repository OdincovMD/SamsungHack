package com.dmiiy.wayapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class tr4_3 extends AppCompatActivity {
    private TextView back,openlink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tr43);
        openlink= (TextView) findViewById(R.id.openlink43);
        openlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://xn--80aaacfpel4cc2n3b.xn--80adxhks/blog/208-dostoprimechatelnosti_muzeya_zapovednika_kolomenskoye.html");
                Intent intent =new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }
}