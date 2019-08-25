package com.aottec.arkot.gps.Notification;

import android.app.NotificationChannel;
import android.net.Uri;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "FCM Service";
    private RemoteMessage remoteMessage1;
    private NotificationChannel channel;
    private Uri uri;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {


    }

    private void sendNotification(RemoteMessage remoteMessage) {


    }
}
