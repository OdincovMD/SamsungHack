package com.dmiiy.wayapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.dmiiy.wayapp.HelperClasses.adapterphone;
import com.dmiiy.wayapp.HelperClasses.phonehelper;
import com.dmiiy.wayapp.tripfirst.FIrstplace;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Random;

public class Trip1 extends AppCompatActivity implements adapterphone.ListItemClickListener{
    private CheckBox checkBox;
private Button btntripback, notify;
    RecyclerView phoneRecycler;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip1);
        phoneRecycler = findViewById(R.id.my_recycler);
        phoneRecycler();
        checkBox=(CheckBox) findViewById(R.id.checkBoxt1);
       btntripback= (Button) findViewById(R.id.buttonback);
       btntripback.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
             //  ShowNotification();

               gotoLocation(view);

           }
       });
       notify=(Button) findViewById(R.id.btnnotify);
       notify.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
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
           }
       });
    }
    public void gotoLocation(View v){
        LatLng goToLocation = new LatLng(34.5392354, 69.1378334);
        LatLng goloc = new LatLng(32.5392354, 69.1378334);
        LatLng golo = new LatLng(30.5392354, 69.1378334);
        LatLng gol = new LatLng(39.5392354, 69.1378334);
        LatLng golon = new LatLng(37.5392354, 69.1378334);
        LatLng golono = new LatLng(45.5392354, 69.1378334);
        LatLng golonan = new LatLng(37.5392354, 65.1378334);
       // LatLng golonana = new LatLng(45.5392354, 60.1378334);
        Bundle args = new Bundle();
        args.putParcelable("latLon", goToLocation);
        args.putString("act","t1");
        args.putString("desc", "Marker in Kabul");
        args.putParcelable("latL", goloc);
        args.putString("de", "Marker in Kabul");
        args.putParcelable("lat", golo);
        args.putString("d", "Marker in Kabul");
        args.putParcelable("la", gol);
        args.putString("des", "Marker in Kabul");
        args.putParcelable("lant", golon);
        args.putString("desca", "Marker in Kabul");
        args.putParcelable("lanta", golono);
        args.putString("descan", "Marker in Kabul");
        args.putParcelable("lantano", golonan);
        args.putString("descara", "Marker in Kabul");
        if (checkBox.isChecked()){
        LatLng golonana = new LatLng(45.5392354, 60.1378334);
        args.putParcelable("lantana", golonana);
        args.putString("descanra", "Marker in Kabul");}
        Intent i = new Intent(this, MapsActivity.class);
        i.putExtras(args);
        startActivity(i);
    }

    private void phoneRecycler() {

        //All Gradients
        GradientDrawable gradient2 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffe5d4b8, 0xffe5d4b8});
        GradientDrawable gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xff7adccf, 0xff7adccf});
        GradientDrawable gradient3 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xfff7c59f, 0xFFf7c59f});
        GradientDrawable gradient4 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffb8d7f5, 0xffb8d7f5});


        phoneRecycler.setHasFixedSize(true);
        phoneRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<phonehelper> phonelocations = new ArrayList<>();
        phonelocations.add(new phonehelper( R.drawable.mgu10, "МГУ"));
        phonelocations.add(new phonehelper(R.drawable.kot11, "Высотка на Котельнической набережной"));
        phonelocations.add(new phonehelper( R.drawable.krasv6, "Высотка на площади Красных Ворот"));
        phonelocations.add(new phonehelper( R.drawable.lenin10, "Гостиница <Ленинградская>"));
        phonelocations.add(new phonehelper( R.drawable.ukr10, "Гостиница <Украина>"));
        phonelocations.add(new phonehelper( R.drawable.kudri10, "Высотка на Кудринской площади"));
        phonelocations.add(new phonehelper( R.drawable.mid10, "МИД"));


        adapter = new adapterphone(phonelocations,this);
        phoneRecycler.setAdapter(adapter);

    }

    @Override
    public void onphoneListClick(int clickedItemIndex) {

        Intent mIntent;
        switch (clickedItemIndex){
            case 0: //first item in Recycler view
            {
                mIntent  = new Intent(Trip1.this, FIrstplace.class);
                startActivity(mIntent);
                break;}
           /* case 1: //second item in Recycler view
                mIntent = new Intent(Trip1.this, vivo.class);
                startActivity(mIntent);
                break;
            case 2: //third item in Recycler view
                mIntent = new Intent(Trip1.this, apple.class);
                startActivity(mIntent);
                break;
            case 3: //third item in Recycler view
                mIntent = new Intent(Trip1.this, realme.class);
                startActivity(mIntent);
                break;
            case 4: //third item in Recycler view
                mIntent = new Intent(Trip1.this, poco.class);
                startActivity(mIntent);
                break;*/

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