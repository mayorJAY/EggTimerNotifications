package com.example.josycom.eggtimernotifications.receiver

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.josycom.eggtimernotifications.R
import com.example.josycom.eggtimernotifications.util.sendNotification

class AlarmReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val notificationManager = ContextCompat.getSystemService(context,
            NotificationManager::class.java) as NotificationManager
        notificationManager.sendNotification(context.getString(R.string.eggs_ready), context)

    }

}