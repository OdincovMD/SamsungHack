package com.dmiiy.wayapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView createanaccount;
    private LinearLayout maillogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createanaccount = (TextView) findViewById(R.id.textView6);
                createanaccount.setOnClickListener(this);
        maillogin=(LinearLayout) findViewById(R.id.MailSignInButton);
                maillogin.setOnClickListener(this);

    }
    @Override
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.textView6: {
                Intent intent = new Intent(MainActivity.this, RegisterUser.class);
                startActivity(intent);
                break;
            }
            case R.id.MailSignInButton: {
                Intent intent = new Intent(MainActivity.this,MailLogin.class);
                startActivity(intent);
                break;
            }

        }
    }
}