package com.montamakk.iposamurai.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.montamakk.iposamurai.R
import com.montamakk.iposamurai.ui.top.MainActivity

class FirebaseNotificationService : FirebaseMessagingService() {
    // プッシュ通知を受け取った際の処理
    override fun onMessageReceived(remoteMessage: RemoteMessage) {

      // Check if message contains a data payload.
      remoteMessage?.data?.isNotEmpty()?.let {
        Log.d(TAG, "Message data payload: " + remoteMessage.data)
      }

      // Check if message contains a notification payload.
      remoteMessage?.notification?.let {
        Log.d(TAG, "Message Notification Body: ${it.body}")
      }

      this.sendNotification()
    }

    // 新しいTokenが作成された際の処理
    override fun onNewToken(token: String) {
      // サーバーに送信するなど
    }

    /**
     * Create and show a simple notification containing the received FCM message.
     *
     */
    private fun sendNotification() {
      val intent = Intent(this, MainActivity::class.java)
      intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
      val pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
        PendingIntent.FLAG_ONE_SHOT)

      val channelId = getString(R.string.default_notification_channel_id)
      val channelName = resources.getString(R.string.default_notification_channel_name)
      val notificationBuilder = NotificationCompat.Builder(this, channelId)
        .setSmallIcon(R.drawable.ic_launcher_foreground)
        .setColor(Color.parseColor("#00ABA9"))
        .setContentTitle(resources.getString(R.string.fcm_title))
        .setContentText(resources.getString(R.string.fcm_content))
        .setShowWhen(true)
        .setWhen(System.currentTimeMillis())
        .setAutoCancel(true)
        .setDefaults(NotificationCompat.DEFAULT_ALL)
        .setPriority(NotificationCompat.PRIORITY_MAX)
        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
        .setContentIntent(pendingIntent)

      val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

      // Since android Oreo notification channel is needed.
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
        channel.description = "description"
        channel.setShowBadge(true)
        channel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
        notificationManager.createNotificationChannel(channel)
      }

      notificationManager.notify(0 /* ID of notification */, notificationBuilder.build())
    }

    companion object {
      private var TAG = "FirebaseMessagingService"
    }
  }