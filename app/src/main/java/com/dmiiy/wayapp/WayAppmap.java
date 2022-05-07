package com.dmiiy.wayapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class WayAppmap extends AppCompatActivity implements View.OnClickListener,Dialogname.ExampleDialogListener {
    private ImageView profilepic;
private Button logoutb;
private TextView usern;
private FirebaseUser user;
private DatabaseReference reference;
private String UID;
private StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_way_appmap);

        usern = (TextView) findViewById(R.id.username);
        usern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
        storageReference=FirebaseStorage.getInstance().getReference();
        StorageReference profileref= storageReference.child("users/"+FirebaseAuth.getInstance().getCurrentUser().getUid()+"/profile.jpg");
        profileref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(profilepic);
            }
        });
        profilepic= (ImageView) findViewById(R.id.profilepic);
        profilepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent opengalleryintent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(opengalleryintent,1000);
            }
        });
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
        final TextView nametext= (TextView) findViewById(R.id.username);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1000){
            if (resultCode== Activity.RESULT_OK){
                Uri imageUri = data.getData();
                //profilepic.setImageURI(imageUri);
                uploadimagetofirebase(imageUri);
            }
        }
    }

    private void uploadimagetofirebase(Uri imageUri) {
        StorageReference fileRef = storageReference.child("users/"+FirebaseAuth.getInstance().getCurrentUser().getUid()+"/profile.jpg");
        fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(profilepic);
                    }
                });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(WayAppmap.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void applyTexts(String username) {
    usern.setText(username);
    }
    public void openDialog() {
        Dialogname exampleDialog = new Dialogname();
        exampleDialog.show(getSupportFragmentManager(), "example dialog");
    }
}