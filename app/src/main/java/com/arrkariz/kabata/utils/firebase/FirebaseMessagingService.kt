package com.arrkariz.kabata.utils.firebase

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.arrkariz.kabata.R
import com.arrkariz.kabata.features.moviesexplore.presentation.HomeActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {
    private val channelId = "FCM Notification"
    private val channelName = "FCM Channel"
    private val notificationId = 1

    override fun onMessageReceived(messge: RemoteMessage) {
        Log.d("FirebaseMessage", "Message received")
        messge.data.isNotEmpty().let {
            Log.d("FirebaseMessage", messge.data.toString())
        }
        sendNotification(messge)

        super.onMessageReceived(messge)
    }

    fun sendNotification(message: RemoteMessage) {
        val intent = Intent(this, HomeActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        val mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val mBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.exo_notification_small_icon)
            .setContentTitle(message.data["title"])
            .setContentText("New Movie!")
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
            channel.description = channelName
            mBuilder.setChannelId(channelId)
            mNotificationManager.createNotificationChannel(channel)
        }

        val notification = mBuilder.build()

        mNotificationManager.notify(notificationId, notification)
    }
}