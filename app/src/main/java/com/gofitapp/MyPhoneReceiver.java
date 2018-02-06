package com.gofitapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;



public class MyPhoneReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().matches("android.location.PROVIDERS_CHANGED")) {
            Toast.makeText(context, "Location Settings Changed!! Please switch on Location for better functionality",
                    Toast.LENGTH_LONG).show();
            showNotification(context);
        }



        }
    private void showNotification(Context context) {
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                new Intent(context, MainActivity.class), 0);
        try {
            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(context)
                            .setSmallIcon(R.drawable.logo2)
                            .setContentTitle("Location Settings Changed")
                            .setContentText("Please Ensure Location Setting is on for better accuracy");
            mBuilder.setContentIntent(contentIntent);
            mBuilder.setDefaults(Notification.DEFAULT_SOUND);
            mBuilder.setAutoCancel(true);
            NotificationManager mNotificationManager =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.notify(1, mBuilder.build());
        }
        catch (Exception e)
        {
            Log.d("Exceptioin",e.getStackTrace()+"");
        }

    }

}