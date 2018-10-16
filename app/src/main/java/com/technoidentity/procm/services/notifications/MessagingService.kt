package com.technoidentity.procm.services.notifications

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MessagingService : FirebaseMessagingService() {

  override fun onMessageReceived(remoteMessage: RemoteMessage?) {

    remoteMessage?.let {
      if (it.data.isNotEmpty()) {
        Log.i("FCM", "PayLoad: " + it.data)
      }

      if (it.notification != null) {
        Log.i("FCM", "Notification: " + it.notification?.body)
      }
    }
  }
}