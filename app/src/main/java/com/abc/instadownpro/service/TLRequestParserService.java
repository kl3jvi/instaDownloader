package com.abc.instadownpro.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import android.text.TextUtils;

import com.abc.instadownpro.R;
import com.abc.instadownpro.activity.MainActivity;
import com.abc.instadownpro.downloader.VideoDownloadFactory;
import com.abc.instadownpro.util.DownloadUtil;
import com.abc.instadownpro.util.URLMatcher;

public class TLRequestParserService extends Service {

    private static final String NOTIFICATION_CHANNEL_SERVICE = "download-notification";

    private NotificationCompat.Builder mBuilder;
    private NotificationManager mNotificationManager;

    private void initNotificationBuilder() {
        Intent mainIntent = new Intent(Intent.ACTION_MAIN);
        mainIntent.setComponent(new ComponentName(this, MainActivity.class));
        PendingIntent disconnectPendingIntent = PendingIntent.getActivity(this, 0, mainIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT), 0);
        mBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_SERVICE);
        mBuilder.setWhen(0);
        mBuilder.setColor(ContextCompat.getColor(this, R.color.black)).setContentIntent(disconnectPendingIntent)
                .setSmallIcon(R.drawable.ins_icon);
        mBuilder.setPriority(NotificationCompat.PRIORITY_LOW);
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    private void showNotification() {
        mBuilder.setTicker("start downloading");
        mBuilder.setContentTitle("start downloading");
        mBuilder.setContentText("start downloading");
        startForeground(1, mBuilder.build());
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // initNotificationBuilder();


    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // showNotification();
        return Service.START_STICKY;
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
