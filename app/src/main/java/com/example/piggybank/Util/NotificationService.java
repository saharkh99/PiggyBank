package com.example.piggybank.Util;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.piggybank.R;
import com.example.piggybank.view.BaseActivity;

import java.util.UUID;

public class NotificationService extends IntentService {
    public static final String TODOTEXT = "com.avjindersekhon.todonotificationservicetext";
    public static final String TODOUUID = "com.avjindersekhon.todonotificationserviceuuid";
    private String mTodoText;
    private String mTodoUUID;
    private Context mContext;

    public NotificationService() {
        super("NotificationService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        mTodoText = intent.getStringExtra(TODOTEXT);
        mTodoUUID = intent.getStringExtra(TODOUUID);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Intent i = new Intent(this, BaseActivity.class);
        i.putExtra(NotificationService.TODOUUID, mTodoUUID);

        Notification notification = new Notification.Builder(this)
                .setContentTitle(mTodoText)
                .setSmallIcon(R.drawable.notification)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setContentIntent(PendingIntent.getActivity(this, mTodoUUID.hashCode(), i, PendingIntent.FLAG_UPDATE_CURRENT))
                .build();

        manager.notify(100, notification);

    }
}