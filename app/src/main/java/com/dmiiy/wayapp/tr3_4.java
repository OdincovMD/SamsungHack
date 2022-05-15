package com.dmiiy.wayapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class tr3_4 extends AppCompatActivity {
    private TextView back,openlink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tr34);
        openlink= (TextView) findViewById(R.id.openlink34);
        openlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://cdm-moscow.ru/history");
                Intent intent =new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }
}