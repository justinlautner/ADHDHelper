package com.example.jay.ADHDHelper;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.VibrationEffect;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class BroadcastManager extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            SharedPreferences sharedPref = context.getSharedPreferences("duty_info", 0);

            String nameString = sharedPref.getString("nameString", "Empty");
            String dateString = sharedPref.getString("dateString", "Empty");
            String timeString = sharedPref.getString("timeString", "Empty");
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            DateFormat hourFormat = new SimpleDateFormat("hh:mm a", Locale.ENGLISH);
            Date date = new Date();
            Date hour = new Date();
            Date myDate = dateFormat.parse(dateString);
            Date myTime = hourFormat.parse(timeString);
            Toast.makeText(context, "TEST", Toast.LENGTH_LONG).show();
            if (date.equals(myDate) && hour.equals(myTime)) {
                Intent it = new Intent(context, MainActivity.class);
                createNotification(context, it, nameString + "is on " + dateString + " at " + timeString, nameString, nameString + "is on " + dateString + " at " + timeString);
            }
        } catch (Exception e) {
            Log.i("date", "error == " + e.getMessage());
        }
    }


    public void createNotification(Context context, Intent intent, CharSequence ticker, CharSequence title, CharSequence descricao) {
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent p = PendingIntent.getActivity(context, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "default");
        builder.setTicker(ticker);
        builder.setContentTitle(title);
        builder.setContentText(descricao);
        builder.setSmallIcon(R.drawable.cool_pineapple);
        builder.setContentIntent(p);
        Notification n = builder.build();
        //create the notification
        //n.vibrate = new long[]{VibrationEffect.DEFAULT_AMPLITUDE};
        //n.vibrate = new long[]{150, 300, 150, 400};
        n.flags = Notification.FLAG_AUTO_CANCEL;
        nm.notify(R.drawable.cool_pineapple, n);
        //create a vibration
        try {

            Uri som = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone toque = RingtoneManager.getRingtone(context, som);
            toque.play();
        } catch (Exception e) {
        }
    }
}