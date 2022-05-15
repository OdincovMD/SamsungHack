package com.dmiiy.wayapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class tr4_2 extends AppCompatActivity {
    private TextView back,openlink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tr42);
        openlink= (TextView) findViewById(R.id.openlink42);
        openlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://ru.wikipedia.org/wiki/%D0%94%D0%BE%D0%BC%D0%B8%D0%BA_%D0%9F%D0%B5%D1%82%D1%80%D0%B0_I_(%D0%9C%D0%BE%D1%81%D0%BA%D0%B2%D0%B0)");
                Intent intent =new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }
}