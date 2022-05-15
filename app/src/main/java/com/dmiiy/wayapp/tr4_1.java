package com.dmiiy.wayapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class tr4_1 extends AppCompatActivity {
    private TextView back,openlink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tr41);
        openlink= (TextView) findViewById(R.id.openlink41);
        openlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://ru.wikipedia.org/wiki/%D0%9A%D0%BE%D0%BB%D0%BE%D0%BC%D0%B5%D0%BD%D1%81%D0%BA%D0%B8%D0%B9_%D0%B4%D0%B2%D0%BE%D1%80%D0%B5%D1%86");
                Intent intent =new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }
}