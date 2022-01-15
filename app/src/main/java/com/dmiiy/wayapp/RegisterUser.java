package com.dmiiy.wayapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener {
private LinearLayout regthroughemail;
private TextView signin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        regthroughemail = (LinearLayout) findViewById(R.id.MailSignInButton);
        regthroughemail.setOnClickListener(this);
        signin = (TextView) findViewById(R.id.textView6);
        signin.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.MailSignInButton: {
                Intent intent = new Intent(RegisterUser.this, RegesterWindow.class);
                startActivity(intent);
                break;
            }
            case R.id.textView6: {
                Intent intent = new Intent(RegisterUser.this, MainActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}