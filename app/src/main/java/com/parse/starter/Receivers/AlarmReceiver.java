package com.parse.starter.Receivers;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.starter.activities.RepeatingActivity;

import java.util.Date;
import java.util.List;



public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(final Context context, Intent intent) {
        ParseQuery<ParseObject> filtro = ParseQuery.getQuery("Produto");
        Date date = new Date();
        filtro.whereLessThan("validade",date);
        filtro.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (!objects.isEmpty() && ParseUser.getCurrentUser()!=null){
                    NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

                    Intent repeating_intent = new Intent(context, RepeatingActivity.class);
                    repeating_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    PendingIntent pendingIntent = PendingIntent.getActivity(context,100, repeating_intent, PendingIntent.FLAG_UPDATE_CURRENT);

                    android.support.v4.app.NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                            .setContentIntent(pendingIntent)
                            .setSmallIcon(android.R.drawable.arrow_up_float)
                            .setContentTitle("Notificação de validade")
                            .setContentText("Produtos fora da validade")
                            .setAutoCancel(true);
                    notificationManager.notify(100,builder.build());


                }
            }
        });




    }
}
