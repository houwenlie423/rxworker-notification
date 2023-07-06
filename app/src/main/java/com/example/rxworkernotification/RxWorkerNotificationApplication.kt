package com.example.rxworkernotification

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version RxWorkerNotificationApplication, v 0.1 Thu 7/6/2023 3:57 PM by Houwen Lie
 */
class RxWorkerNotificationApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initializeNotificationChannel()
    }

    private fun initializeNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                getString(R.string.default_notification_channel_id),
                getString(R.string.default_notification_channel_title),
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = getString(R.string.default_notification_channel_description)
            }

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}