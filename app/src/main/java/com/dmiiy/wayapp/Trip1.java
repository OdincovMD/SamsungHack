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
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

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
    private TextView startdialog,back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip1);
        phoneRecycler = findViewById(R.id.my_recycler);
        phoneRecycler();
        checkBox=(CheckBox) findViewById(R.id.checkBoxt1);
        back=(TextView)findViewById(R.id.backtoregusersfromtrip1);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Trip1.this,MapsActivity.class);
                startActivity(intent);
            }
        });
       btntripback= (Button) findViewById(R.id.buttonbacktr1);
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
        startdialog=(TextView) findViewById(R.id.alertdialoginfotrip1);
        startdialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog =new AlertDialog.Builder(Trip1.this);
                dialog.setTitle("Наследие Сталина");
                dialog.setMessage("Это уникальная экскурсионная программа, которая была составлена с учетом многих факторов. Вы сможете посетить все Сталинские высотки, которые были постоены. Также по-вашему желанию (отметив галочкой ^Добавить посещение кафе^ ) на карте также будет отмечено шикарное место, где вы сможете прочувствовать атмосферу СССР и заодно вкусно покушать ! P.S.:информацию о всех высотках вы можете посмотреть не выходя с этой странички, вам надо только нажать на картинку обЪкта о котором вы хотите узнать. + Бонус: ссылки на дополнительную информацию! Удачи в путешествиях!");
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
        LatLng goToLocation = new LatLng(55.702888, 37.530582);
        LatLng goloc = new LatLng(55.747153, 37.642825);
        LatLng golo = new LatLng(55.769512, 37.649268);
        LatLng gol = new LatLng(55.774089, 37.651642);
        LatLng golon = new LatLng(55.751419, 37.565557);
        LatLng golono = new LatLng(55.759235, 37.580903);
        LatLng golonan = new LatLng(55.745654, 37.584859);
       // LatLng golonana = new LatLng(45.5392354, 60.1378334);
        Bundle args = new Bundle();
        args.putString("t1","1");
        args.putParcelable("latLon", goToLocation);
        args.putString("desc", "МГУ");

        args.putParcelable("latL", goloc);
        args.putString("de", "Высотка на Котельнической набережной");

        args.putParcelable("lat", golo);
        args.putString("d", "Высотка на площади Красных Ворот");

        args.putParcelable("la", gol);
        args.putString("des", "Гостиница <Ленинградская>");

        args.putParcelable("lant", golon);
        args.putString("desca", "Гостиница <Украина>");
        args.putParcelable("lanta", golono);
        args.putString("descan", "Высотка на Кудринской площади");
        args.putParcelable("lantano", golonan);
        args.putString("descara", "МИД ");
        if (checkBox.isChecked()){
        LatLng golonana = new LatLng(55.749877, 37.593471);
        args.putParcelable("lantana", golonana);
        args.putString("descanra", "Вареничная №1");
            //args.putString("check1","1");
        }

        Intent i = new Intent(this, createroadsplash.class);
        i.putExtras(args);
        startActivity(i);
    }

    private void phoneRecycler() {




        phoneRecycler.setHasFixedSize(true);
        phoneRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<phonehelper> phonelocations = new ArrayList<>();
        phonelocations.add(new phonehelper( R.drawable.mgu10, "№1 МГУ"));
        phonelocations.add(new phonehelper(R.drawable.kot11, "№2 Высотка на Котельнической набережной"));
        phonelocations.add(new phonehelper( R.drawable.krasv6, "№3 Высотка на площади Красных Ворот"));
        phonelocations.add(new phonehelper( R.drawable.lenin10, "№4 Гостиница <Ленинградская>"));
        phonelocations.add(new phonehelper( R.drawable.ukr10, "№5 Гостиница <Украина>"));
        phonelocations.add(new phonehelper( R.drawable.kudri10, "№6 Высотка на Кудринской площади"));
        phonelocations.add(new phonehelper( R.drawable.mid10, "№7 МИД"));


        adapter = new adapterphone(phonelocations,this);
        phoneRecycler.setAdapter(adapter);

    }

    @Override
    public void onphoneListClick(int clickedItemIndex) {

        Intent mIntent;
        switch (clickedItemIndex){
           case 0: //first item in Recycler view
           {
               mIntent  = new Intent(Trip1.this, tr1_1.class);
               startActivity(mIntent);
                break;}
          case 1: //second item in Recycler view
                mIntent = new Intent(Trip1.this, tr1_2.class);
                startActivity(mIntent);
                break;
            case 2: //third item in Recycler view
                mIntent = new Intent(Trip1.this, tr1_3.class);
                startActivity(mIntent);
                break;
            case 3: //third item in Recycler view
                mIntent = new Intent(Trip1.this, tr1_4.class);
                startActivity(mIntent);
                break;
            case 4: //third item in Recycler view
                mIntent = new Intent(Trip1.this, tr1_5.class);
                startActivity(mIntent);
                break;
            case 5: //third item in Recycler view
                mIntent = new Intent(Trip1.this, tr1_6.class);
                startActivity(mIntent);
                break;
            case 6: //third item in Recycler view
                mIntent = new Intent(Trip1.this, tr1_7.class);
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