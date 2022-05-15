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

public class Trip4 extends AppCompatActivity implements adaptertrip.ListItemClickListener{
    private CheckBox checkBox;
    private Button btntripback, notify;
    RecyclerView phoneRecycler;
    RecyclerView.Adapter adapter;
    private TextView startdialog,back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip4);
        phoneRecycler = findViewById(R.id.my_recycler4);
        phoneRecycler();
        checkBox=(CheckBox) findViewById(R.id.checkBoxt4);
        back=(TextView)findViewById(R.id.backtoregusersfromtrip4);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Trip4.this,MapsActivity.class);
                startActivity(intent);
            }
        });
        btntripback= (Button) findViewById(R.id.buttonbacktr4);
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
        startdialog=(TextView) findViewById(R.id.alertdialoginfotrip4);
        startdialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog =new AlertDialog.Builder(Trip4.this);
                dialog.setTitle("Старые времена");
                dialog.setMessage("Этот тур предполагает посещение прекрасного места под названием ^Коломенское^.Здесь вы сможете прогуляться по красивому парку и насладиться строениями прошлых веков. Это прогулка освободит вас суеты современного мира ! Также по-вашему желанию (отметив галочкой ^Добавить посещение кафе^ ) на карте также будет отмечено шикарное место, где вы сможете пнасладиться настоящими русскими блюдами. P.S.: Я вам отметил самые интересные места и по нажатию на них вы сможете узнать информацию. + Бонус: ссылки на дополнительную информацию! Удачи в путешествиях!");
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
        LatLng goToLocation = new LatLng(55.661760, 37.663445);

        Bundle args = new Bundle();
        args.putString("t4","1");
        args.putParcelable("latLon", goToLocation);
        args.putString("desc", "Музей-заповедник ^Коломенское^");

        if (checkBox.isChecked()){
            LatLng goloc = new LatLng(55.651841, 37.604651);
            args.putParcelable("latL", goloc);
            args.putString("de", "Ресторан ^Русское Подворье^");
            //args.putString("check1","1");
        }

        Intent i = new Intent(this, createroadsplash.class);
        i.putExtras(args);
        startActivity(i);
        finish();
    }

    private void phoneRecycler() {


        phoneRecycler.setHasFixedSize(true);
        phoneRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<triphelper> phonelocations = new ArrayList<>();
        phonelocations.add(new triphelper( R.drawable.kolom11, "Деревянный дворец царя Алексея Михайловича"));
        phonelocations.add(new triphelper(R.drawable.domik, "Домик Петра I"));
        phonelocations.add(new triphelper( R.drawable.palata, "Полковничьи палаты"));


        adapter = new adaptertrip(phonelocations,this);
        phoneRecycler.setAdapter(adapter);

    }

    @Override
    public void onphoneListClick(int clickedItemIndex) {

        Intent mIntent;
        switch (clickedItemIndex){
            case 0: //first item in Recycler view
             {
             mIntent  = new Intent(Trip4.this, tr4_1.class);
            startActivity(mIntent);
             break;}
            case 1: //second item in Recycler view
                mIntent = new Intent(Trip4.this, tr4_2.class);
                startActivity(mIntent);
                break;
            case 2: //third item in Recycler view
                mIntent = new Intent(Trip4.this, tr4_3.class);
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