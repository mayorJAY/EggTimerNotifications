package com.example.josycom.eggtimernotifications.util

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import com.example.josycom.eggtimernotifications.MainActivity
import com.example.josycom.eggtimernotifications.R
import com.example.josycom.eggtimernotifications.receiver.SnoozeReceiver

// Notification ID.
private val NOTIFICATION_ID = 0
private val REQUEST_CODE = 0
private val FLAGS = 0

/**
 * Builds and delivers the notification.
 *
 * @param context, activity context.
 */
fun NotificationManager.sendNotification(messageBody: String, applicationContext: Context) {
    // Create the content intent for the notification, which launches
    // this activity
    val contentIntent = Intent(applicationContext, MainActivity::class.java)

    val contentPendingIntent = PendingIntent.getActivity(applicationContext,
        NOTIFICATION_ID,
        contentIntent,
        PendingIntent.FLAG_UPDATE_CURRENT)

    val eggImage = BitmapFactory.decodeResource(applicationContext.resources, R.drawable.cooked_egg)
    val bigPicStyle = NotificationCompat.BigPictureStyle()
        .bigPicture(eggImage)
        .bigLargeIcon(null)

    val snoozeIntent = Intent(applicationContext, SnoozeReceiver::class.java)
    val snoozePendingIntent = PendingIntent.getBroadcast(applicationContext, REQUEST_CODE, snoozeIntent, FLAGS)

    // Build the notification
    val builder = NotificationCompat.Builder(applicationContext,
            applicationContext.getString(R.string.egg_notification_channel_id))
        .setSmallIcon(R.drawable.cooked_egg)
        .setContentTitle(applicationContext.getString(R.string.notification_title))
        .setContentText(messageBody)
        .setContentIntent(contentPendingIntent)
        .setAutoCancel(true)
        .setStyle(bigPicStyle)
        .setLargeIcon(eggImage)
        .addAction(R.drawable.egg_icon, applicationContext.getString(R.string.snooze), snoozePendingIntent)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        notify(NOTIFICATION_ID, builder.build())

}

fun NotificationManager.cancelNotifications() {
    cancelAll()
}
