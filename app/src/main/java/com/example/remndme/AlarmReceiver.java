package com.example.remndme;

import android.app.NotificationChannel;
import android.app.NotificationManager;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {
    private static final String CHANNEL_ONE = "ch1";
    private static final String CHANNEL_TWO = "ch2";
    NotificationChannel channel;
    NotificationCompat.Builder builder;

    @Override
    public void onReceive(Context context, Intent intent) {

        //get id & title of the alarm
        int notificationId = intent.getIntExtra("notificationId", 0);
        String title = intent.getStringExtra("title");
        int impo = intent.getIntExtra("importance", 0);
        String date = intent.getStringExtra("date");
        String time = intent.getStringExtra("time");


        //call SecondaryActivity when notification is tapped
        Intent mainIntent = new Intent(context, ReminderDetailsActivity.class);
        mainIntent.putExtra("titletask", title);
        mainIntent.putExtra("impo", impo);
        mainIntent.putExtra("date", date);
        mainIntent.putExtra("time", time);


        mainIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent contentIntent = PendingIntent.getActivity(
                context, 0, mainIntent, PendingIntent.FLAG_UPDATE_CURRENT
        );

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        int importance = 0;

        String Reminder_title = "Your Task Reminder";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // for API 26 AND ABOVE
            CharSequence channel_name = "My Notification";
            switch (impo) {
                case 0:
                    channel = new NotificationChannel(CHANNEL_ONE, channel_name, NotificationManager.IMPORTANCE_HIGH);
                    notificationManager.createNotificationChannel(channel);
                    builder = new NotificationCompat.Builder(context, CHANNEL_ONE)
                            .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)
                            .setContentTitle(Reminder_title)
                            .setContentText(title)
                            .setContentIntent(contentIntent)
                            .setPriority(NotificationCompat.PRIORITY_HIGH)
                            .setAutoCancel(true);
                    break;
                case 1:
                    importance = NotificationManager.IMPORTANCE_LOW;
                    channel = new NotificationChannel(CHANNEL_TWO, channel_name, importance);
                    notificationManager.createNotificationChannel(channel);
                    builder = new NotificationCompat.Builder(context, CHANNEL_TWO)
                            .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)
                            .setContentTitle(Reminder_title)
                            .setContentText(title)
                            .setContentIntent(contentIntent)
                            .setPriority(NotificationCompat.PRIORITY_LOW)
                            .setAutoCancel(true);
                    break;
            }

        }

        //notify
        notificationManager.notify(notificationId, builder.build());
    }
}
