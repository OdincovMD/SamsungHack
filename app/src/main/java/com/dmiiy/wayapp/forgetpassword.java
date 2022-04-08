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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgetpassword extends AppCompatActivity {
private EditText emailfor;
private Button buttonfor;
private ProgressBar progbarfor;
FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);
        emailfor = (EditText) findViewById(R.id.Emailforget);
        buttonfor= (Button) findViewById(R.id.buttonforget);
        progbarfor= (ProgressBar) findViewById(R.id.progressBarforgetpas);
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
                    Toast.makeText(forgetpassword.this, "Проверьте почту для восстановления пароля", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(forgetpassword.this,MailLogin.class));
                }else {
                    Toast.makeText(forgetpassword.this, "Попробуйте ещё раз! Что-то пошло не так", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}