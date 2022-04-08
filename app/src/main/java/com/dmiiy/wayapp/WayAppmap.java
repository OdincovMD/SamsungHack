package com.dmiiy.wayapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WayAppmap extends AppCompatActivity {
private Button logoutb;
private FirebaseUser user;
private DatabaseReference reference;
private String UID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_way_appmap);
        logoutb = (Button) findViewById(R.id.logout);
        logoutb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(WayAppmap.this,MainActivity.class));
            }
        });
        user =FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance("https://wayapp-a6d5d-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Users");
        UID=user.getUid();
        final TextView nametext= (TextView) findViewById(R.id.textName);
        final TextView emailtext = (TextView) findViewById(R.id.textEmail);
        reference.child(UID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserMailreg userprof= dataSnapshot.getValue(UserMailreg.class);
                if (userprof!= null){
                    String name = userprof.name;
                    String email =userprof.email;
                    nametext.setText(name);
                    emailtext.setText(email);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(WayAppmap.this, "Что-то пошло не так!", Toast.LENGTH_LONG).show();
            }
        });
    }
}