package com.dmiiy.wayapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.amrdeveloper.lottiedialog.LottieDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
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
private Button logoutb, deletebtn;
private TextView usern;
private FirebaseUser user,prof;
private DatabaseReference reference,del;
private String UID;
private ProgressBar progressBar;
private StorageReference storageReference;
private LinearLayout support,about;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_way_appmap);
        progressBar=(ProgressBar)findViewById(R.id.progressBarprofile) ;
        progressBar.setVisibility(View.VISIBLE);
        deletebtn =(Button) findViewById(R.id.deleteac);

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
                progressBar.setVisibility(View.GONE);
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
                startActivity(new Intent(WayAppmap.this,MailLogin.class));
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
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(WayAppmap.this, "Что-то пошло не так!", Toast.LENGTH_LONG).show();
            }
        });
        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog =new AlertDialog.Builder(WayAppmap.this);
                dialog.setTitle("Вы уверены?");
                dialog.setMessage("При удаление аккаунта дальнейший вход будет невозможен.Нужна повторная регистрация !");
                dialog.setPositiveButton("Удалить", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        prof =FirebaseAuth.getInstance().getCurrentUser();
                        deletefromdatabase(prof.getUid());
                        deletepict(storageReference);
                        prof.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    //Toast.makeText(WayAppmap.this, "Goodbye", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(WayAppmap.this,MailLogin.class));
                                }else {
                                    Toast.makeText(WayAppmap.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
               dialog.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                     dialogInterface.dismiss();
                   }
               });
               AlertDialog alertDialog=dialog.create();
               alertDialog.show();
            }
        });
    }

    private void deletepict(StorageReference storageReference) {
        StorageReference delteprofpic= storageReference.child("users/"+FirebaseAuth.getInstance().getCurrentUser().getUid()+"/profile.jpg");
        delteprofpic.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                   // Toast.makeText(WayAppmap.this, "Gb", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(WayAppmap.this,MailLogin.class));
                }else {
                    Toast.makeText(WayAppmap.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void deletefromdatabase(String uid) {
        del= FirebaseDatabase.getInstance("https://wayapp-a6d5d-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Users").child(uid);
        del.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                  //  Toast.makeText(WayAppmap.this, "fb", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(WayAppmap.this,MailLogin.class));
                }else {
                    Toast.makeText(WayAppmap.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1000){
            if (resultCode== Activity.RESULT_OK){
                Uri imageUri = data.getData();
                profilepic.setImageURI(imageUri);//changes i did
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