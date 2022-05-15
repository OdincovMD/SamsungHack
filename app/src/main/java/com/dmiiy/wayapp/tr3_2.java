package com.dmiiy.wayapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class tr3_2 extends AppCompatActivity {
    private TextView back,openlink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tr32);
        openlink= (TextView) findViewById(R.id.openlink32);
        openlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://poraionu.ru/info/red-platz-history/#:~:text=%D0%A1%D0%B0%D0%BC%D0%B0%D1%8F%20%D1%80%D0%B0%D1%81%D0%BF%D1%80%D0%BE%D1%81%D1%82%D1%80%D0%B0%D0%BD%D0%B5%D0%BD%D0%BD%D0%B0%D1%8F%20%D0%B2%D0%B5%D1%80%D1%81%D0%B8%D1%8F%20%D0%B3%D0%BB%D0%B0%D1%81%D0%B8%D1%82%2C%20%D1%87%D1%82%D0%BE,%D0%BF%D0%BE%D1%8F%D0%B2%D0%B8%D0%BB%D0%B0%D1%81%D1%8C%20%D0%BF%D0%BB%D0%BE%D1%89%D0%B0%D0%B4%D1%8C%2D%D0%BF%D1%80%D0%B5%D0%B4%D1%88%D0%B5%D1%81%D1%82%D0%B2%D0%B5%D0%BD%D0%BD%D0%B8%D1%86%D0%B0%20%D0%9A%D1%80%D0%B0%D1%81%D0%BD%D0%BE%D0%B9%20%D0%BF%D0%BB%D0%BE%D1%89%D0%B0%D0%B4%D0%B8.");
                Intent intent =new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }
}