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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

public class RegesterWindow extends AppCompatActivity implements View.OnClickListener {
private TextView back,emailsignin;
private FirebaseAuth mAuth;
private ProgressBar progressBar;
private MaterialEditText editname,editemail,editpassword;
private Button buttonreg;
private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regester_window);
        back=(TextView) findViewById(R.id.backtoregusersfromemailreg);
        back.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();


        emailsignin= (TextView) findViewById(R.id.textemailreg);
        editemail = (MaterialEditText) findViewById(R.id.Emailemailreg);
        editpassword = (MaterialEditText) findViewById(R.id.Passwordemailreg);
        editname = (MaterialEditText) findViewById(R.id.Nameemailreg);
        progressBar = (ProgressBar) findViewById(R.id.progressBarEmailreg);
        buttonreg =(Button) findViewById(R.id.SignInemailreg);
        buttonreg.setOnClickListener(this);
        mDatabase = FirebaseDatabase.getInstance("https://wayapp-a6d5d-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Users");
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backtoregusersfromemailreg: {
                Intent intent = new Intent(RegesterWindow.this, MailLogin.class);
                startActivity(intent);
                break;
            }
            case R.id.SignInemailreg: {
                registerUserEmail();
                break;
            }
        }
    }
    private void registerUserEmail(){
        String email = editemail.getText().toString().trim();
        String password = editpassword.getText().toString().trim();
        String name = editname.getText().toString().trim();

        if(name.isEmpty()){
            editname.setError("Заполните строку с именем!");
            editname.requestFocus();
            return;
        }
        if(email.isEmpty()){
            editemail.setError("Заполните строку с E-mail!");
            editemail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editemail.setError("E-mail введён неверно!");
            editemail.requestFocus();
            return;
        }
        if (password.isEmpty()){
            editpassword.setError("Заполните строку с паролем!");
            editpassword.requestFocus();
            return;
        }
        if(password.length()<6){
            editpassword.setError("Пароль не может быть меньше 6!");
            editpassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    UserMailreg user = new UserMailreg(name, email);

                    mDatabase.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                               // Toast.makeText(RegesterWindow.this, "Вы успешно зарегистриваны!", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                Intent i =new Intent(getApplicationContext(),splashcreateaccount.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(RegesterWindow.this, "Ошибка регистрации! Попробуйте ещё раз!", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }

                        }
                    });
                }
                else{
                    Toast.makeText(RegesterWindow.this, "Ошибка регистрации!", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }
}