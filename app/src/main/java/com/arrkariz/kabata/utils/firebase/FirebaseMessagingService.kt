package com.arrkariz.kabata.utils.firebase

import android.content.Intent
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)

    }

    override fun onMessageReceived(messge: RemoteMessage) {
        Log.d("FirebaseMessage", "Message received")
        messge.data.isNotEmpty().let {
            Log.d("FirebaseMessage", messge.data.toString())
        }

        super.onMessageReceived(messge)
    }
}