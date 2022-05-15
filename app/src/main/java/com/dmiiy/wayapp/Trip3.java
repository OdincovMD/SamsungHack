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

public class Trip3 extends AppCompatActivity implements adaptertrip.ListItemClickListener{
    private CheckBox checkBox;
    private Button btntripback, notify;
    RecyclerView tripRecycler;
    RecyclerView.Adapter adapter;
    private TextView startdialog,back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip3);
        tripRecycler = findViewById(R.id.my_recycler3);
        phoneRecycler();
        checkBox=(CheckBox) findViewById(R.id.checkBoxt3);
        back=(TextView)findViewById(R.id.backtoregusersfromtrip3);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Trip3.this,MapsActivity.class);
                startActivity(intent);
            }
        });
        btntripback= (Button) findViewById(R.id.buttonbacktr3);
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
        startdialog=(TextView) findViewById(R.id.alertdialoginfotrip3);
        startdialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog =new AlertDialog.Builder(Trip3.this);
                dialog.setTitle("Топ мест Москвы");
                dialog.setMessage("Это топ мест,которые обязан посетить каждый турист или житель города. Эти места заслуживают вашего внимания. И обязательно надо сказать,что в этих местах получаются просто шикарные фотографии! Также по-вашему желанию (отметив галочкой ^Добавить посещение кафе^ ) на карте также будет отмечено шикарное место, где вы сможете покушать вкусную русскую еду! Мне кажется этот маршрут очень насыщенный. P.S.:информацию о всех местах вы можете посмотреть не выходя с этой странички, вам надо только нажать на картинку обЪекта, о котором вы хотите узнать. + Бонус: ссылки на дополнительную информацию! Удачи в путешествиях!");
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
        LatLng goToLocation = new LatLng(55.751038, 37.628193);
        LatLng goloc = new LatLng(55.753110, 37.621745);
        LatLng golo = new LatLng(55.760143, 37.618507);
        LatLng gol = new LatLng(55.760188, 37.625138);
        //LatLng golon = new LatLng(55.751419, 37.565557);


        Bundle args = new Bundle();
        args.putString("t3","1");
        args.putParcelable("latLon", goToLocation);
        args.putString("desc", "Парк Зарядье");

        args.putParcelable("latL", goloc);
        args.putString("de", "Красная площадь");

        args.putParcelable("lat", golo);
        args.putString("d", "Большой театр");

        args.putParcelable("la", gol);
        args.putString("des", "Центральный детский мир");
        if (checkBox.isChecked()){
            LatLng golonana = new LatLng(55.763293, 37.615074);
            args.putParcelable("lant", golonana);
            args.putString("desca", "ЛЕПИМ и ВАРИМ ");
            //args.putString("check1","1");
        }

        Intent i = new Intent(this, createroadsplash.class);
        i.putExtras(args);
        startActivity(i);
        finish();
    }

    private void phoneRecycler() {




        tripRecycler.setHasFixedSize(true);
        tripRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<triphelper> phonelocations = new ArrayList<>();
        phonelocations.add(new triphelper( R.drawable.zar, "№1 Парк Зарядье"));
        phonelocations.add(new triphelper(R.drawable.plosh, "№2 Красная площадь"));
        phonelocations.add(new triphelper( R.drawable.theatre, "№3 Большой театр"));
        phonelocations.add(new triphelper( R.drawable.cdm, "№4 Центральный детский мир"));

        adapter = new adaptertrip(phonelocations,this);
        tripRecycler.setAdapter(adapter);

    }

    @Override
    public void onphoneListClick(int clickedItemIndex) {
        Intent mIntent;
        switch (clickedItemIndex){
            case 0: //first item in Recycler view
            {
                mIntent  = new Intent(Trip3.this, tr3_1.class);
                startActivity(mIntent);
                break;}
            case 1: //second item in Recycler view
                mIntent = new Intent(Trip3.this, tr3_2.class);
                startActivity(mIntent);
                break;
            case 2: //third item in Recycler view
                mIntent = new Intent(Trip3.this, tr3_3.class);
                startActivity(mIntent);
                break;
            case 3: //third item in Recycler view
                mIntent = new Intent(Trip3.this, tr3_4.class);
                startActivity(mIntent);
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