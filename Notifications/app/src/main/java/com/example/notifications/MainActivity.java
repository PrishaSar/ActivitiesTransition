package com.example.notifications;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.content.Intent;


public class MainActivity extends AppCompatActivity {

    public static int nCount = 0;

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.notifButton);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.POST_NOTIFICATIONS)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, 101);

            }
        }

    }

    //when the app closes, this is what we do:
    @Override
    protected void onStop () {
        super.onStop();
        startService(new Intent(this, NotificationService.class));
    }
    public void closeApp (View view) {
        finish();
    }


    /////+___s=a9dfdsmvgvdfvtyghujre tyrtyb


    public void startNotifLoop(View v){

//      p2:  Intent stateIntent = new Intent(this, GotchiBroadcastReceiver.class);
//      p2:  stateIntent.putExtra("id", 100);
//      p2:  PendingIntent pendingIntent =
//      p2:          PendingIntent.getBroadcast(this, 0, stateIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        new CountDownTimer(30000, 7000) {
            public void onTick(long millisUntilFinished) {
                makeNotification(v);
                Log.i("gotchi", "notif given");
            }
            @Override
            public void onFinish() {
                Log.i("gotchi", "Done being tired!");
            }

        }.start();
    }


    ///need to set first notif, and then after put notifs in broadcast!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    public void makeNotification(View v){
        String CHANNEL_ID = "TEST_ID";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_android_pink)
            .setContentTitle("Test Notif")
            .setContentText("hellloo worlddd!!")
            .setAutoCancel(true).setPriority(NotificationCompat.PRIORITY_DEFAULT);
//         p2:       .addAction(R.drawable.ic_launcher_background,"hello", pendingIntent);

        Intent intent = new Intent(getApplicationContext(), AlertDetails.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("data (if needed?)", "smttttt");

        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_MUTABLE);

        builder.setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        ////don't need this

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = notificationManager.getNotificationChannel(CHANNEL_ID);
            if(notificationChannel == null){

                int importance = NotificationManager.IMPORTANCE_DEFAULT;
                notificationChannel = new NotificationChannel(CHANNEL_ID, "idk a description", importance);
                notificationChannel.enableVibration(true);
                notificationManager.createNotificationChannel(notificationChannel);

            }
        }

        notificationManager.notify(nCount, builder.build());
        nCount ++;
        Log.i("gotchi", "notif passed!");



    }


}