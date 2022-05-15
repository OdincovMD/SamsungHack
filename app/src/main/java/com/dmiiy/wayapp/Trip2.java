package com.dmiiy.wayapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.dmiiy.wayapp.HelperClasses.adaptertrip;
import com.dmiiy.wayapp.HelperClasses.triphelper;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Random;

public class Trip2 extends AppCompatActivity implements adaptertrip.ListItemClickListener{
    private CheckBox checkBox;
    private Button btntripback, notify;
    RecyclerView phoneRecycler;
    RecyclerView.Adapter adapter;
    private TextView startdialog,back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip2);
        phoneRecycler = findViewById(R.id.my_recycler2);
        phoneRecycler();
        back=(TextView)findViewById(R.id.backtoregusersfromtrip2);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Trip2.this,MapsActivity.class);
                startActivity(intent);
            }
        });
        btntripback= (Button) findViewById(R.id.buttonbacktr2);
        btntripback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  ShowNotification();

                gotoLocation(view);

            }
        });
       /*notify=(Button) findViewById(R.id.btnnotify);
       notify.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {*/
        // ShowNotification();
              /* String message="This is a notification example";
                NotificationCompat.Builder builder=new NotificationCompat.Builder(
                        Trip1.this
                )
                        .setSmallIcon(R.drawable.ic_baseline_person_menu)
                        .setContentTitle("New notification")
                        .setContentText(message)
                        .setAutoCancel(true);

                Intent intent= new Intent(Trip1.this,MapsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("message",message);
                PendingIntent pendingIntent= PendingIntent.getActivity(Trip1.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pendingIntent);
                NotificationManager notificationManager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(0,builder.build());*/
        //   }
        //   });
        startdialog=(TextView) findViewById(R.id.alertdialoginfotrip2);
        startdialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog =new AlertDialog.Builder(Trip2.this);
                dialog.setTitle("Погружение в Азию");
                dialog.setMessage("Это уникальная в своем виде программа, которая сможет вас погрузить в азиатскую культуру даже не уезжая из Москвы. Маршрут предполагает посещение 3 мест. №1-Чихо, здесь я вам рекомендую пообедать и насладится атмосферой настоящего Китая. №2- RAMEN - это уже заведение с японской кухней,вкотором можно насладиться нежными десертами,которые тесно связаны со страной восходящего солнца. №3-НИППОН- японский супермаркет в центре Москвы.Там можно набрать кучу вкусняшек,которые можно потрескать дома. Это замечательное путешествие по азиатской гастрономии:) Удачи в путешествиях!");
                dialog.setNegativeButton("Закрыть", new DialogInterface.OnClickListener() {
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
    public void gotoLocation(View v){
        LatLng goToLocation = new LatLng(55.761945, 37.635885);
        LatLng goloc = new LatLng(55.772174, 37.619604);
        LatLng golo = new LatLng(55.769323, 37.620780);

        Bundle args = new Bundle();
        args.putString("t2","1");
        args.putParcelable("latLon", goToLocation);
        args.putString("desc", "ЧИХО");

        args.putParcelable("latL", goloc);
        args.putString("de", "RAMEN");

        args.putParcelable("lat", golo);
        args.putString("d", "НИППОН");


        Intent i = new Intent(this, createroadsplash.class);
        i.putExtras(args);
        startActivity(i);
        finish();
    }

    private void phoneRecycler() {




        phoneRecycler.setHasFixedSize(true);
        phoneRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<triphelper> phonelocations = new ArrayList<>();
        phonelocations.add(new triphelper( R.drawable.chiho1, "№1 ЧИХО"));
        phonelocations.add(new triphelper(R.drawable.ram, "№2 RAMEN"));
        phonelocations.add(new triphelper( R.drawable.nip1, "№3 НИППОН"));



        adapter = new adaptertrip(phonelocations,this);
        phoneRecycler.setAdapter(adapter);

    }

    @Override
    public void onphoneListClick(int clickedItemIndex) {

        Intent mIntent;
        switch (clickedItemIndex){
            case 0: //first item in Recycler view
             {
                 Uri uri = Uri.parse("https://chiho.ru/");
                 Intent inten =new Intent(Intent.ACTION_VIEW, uri);
                 startActivity(inten);
             break;
             }
           case 1: //second item in Recycler view
               Uri urii = Uri.parse("https://t.me/ramenmoscow");
               Intent intent =new Intent(Intent.ACTION_VIEW, urii);
               startActivity(intent);
                break;
            case 2: //third item in Recycler view
                Uri uriii = Uri.parse("https://nippononline.ru/");
                Intent inte =new Intent(Intent.ACTION_VIEW, uriii);
                startActivity(inte);
                break;

        }


    }
    private void ShowNotification(){
        int notificationId=new Random().nextInt(100);
        String channelId = "notification_channel_1";
        NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent= new Intent(getApplicationContext(),MapsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent=PendingIntent.getActivity(getApplicationContext(),0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(
                getApplicationContext(),channelId
        );
        builder.setSmallIcon(R.drawable.logo);
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        builder.setContentTitle("fmdknfrsirgtr");
        builder.setContentText("ddldkdkdddddddddddddd");
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);
        builder.setPriority(NotificationCompat.PRIORITY_MAX);
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            if (notificationManager != null && notificationManager.getNotificationChannel(channelId)==null){
                NotificationChannel notificationChannel= new NotificationChannel(
                        channelId,"Notification Channel 1",
                        NotificationManager.IMPORTANCE_HIGH
                );
                notificationChannel.setDescription("THis note channel is used to notify you");
                notificationChannel.enableVibration(true);
                notificationChannel.enableLights(true);
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }
        Notification notification= builder.build();
        if (notificationManager!= null){
            notificationManager.notify(notificationId,notification);
        }
    }
}