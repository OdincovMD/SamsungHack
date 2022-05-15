package com.dmiiy.wayapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.rengwuxian.materialedittext.MaterialEditText;

public class forgetpassword extends AppCompatActivity {
private MaterialEditText emailfor;
private Button buttonfor;
private ProgressBar progbarfor;
private TextView back;
FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);
        emailfor = (MaterialEditText) findViewById(R.id.Emailforget);
        buttonfor= (Button) findViewById(R.id.buttonforget);
        progbarfor= (ProgressBar) findViewById(R.id.progressBarforgetpas);
        back=(TextView)findViewById(R.id.backtoregusersfromforgetpas) ;
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),MailLogin.class);
                startActivity(i);
            }
        });
        auth= FirebaseAuth.getInstance();
        buttonfor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }
        });
    }

    private void resetPassword() {
        String email = emailfor.getText().toString().trim();
        if (email.isEmpty()){
            emailfor.setError("Почта не введена!");
            emailfor.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
           emailfor.setError("Почта не действительна");
           emailfor.requestFocus();
           return;
        }
        progbarfor.setVisibility(View.VISIBLE);
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                   // Toast.makeText(forgetpassword.this, "Проверьте свою почту для восстановления пароля", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(forgetpassword.this,sendletter.class));
                    finish();
                }else {
                    Toast.makeText(forgetpassword.this, "Попробуйте ещё раз! Что-то пошло не так", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}