package com.example.rxworkernotification.notification

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import androidx.annotation.DrawableRes
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.rxworkernotification.R
import kotlin.random.Random


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version NotificationUtil, v 0.1 Thu 7/6/2023 4:58 PM by Houwen Lie
 */
object NotificationUtil {

    // Read https://developer.android.com/reference/android/app/Notification.Builder#addAction(android.app.Notification.Action)
    private const val MAXIMUM_ACTIONS = 3

    fun showNotification(
        context: Context,
        title: String,
        message: String,
        @DrawableRes smallIcon: Int = R.drawable.ic_sport_kabaddi,
        contentIntent: PendingIntent? = null,
        actions: List<NotificationCompat.Action> = emptyList(),
        autoCancel: Boolean = true,
        tag: String = "",
        notificationId: Int = 322
    ) {
        val channelId = context.getString(R.string.default_notification_channel_id)
        val builder = NotificationCompat.Builder(context, channelId)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(smallIcon)
            .setContentIntent(contentIntent)
            .setAutoCancel(autoCancel)

        actions.take(MAXIMUM_ACTIONS).forEach { action -> builder.addAction(action) }

        if (tag.isEmpty()) {
            getNotificationManager(context).notify(notificationId, builder.build())
        } else {
            getNotificationManager(context).notify(tag, notificationId, builder.build())
        }

    }

    fun clearAllNotifications(context: Context) {
        getNotificationManager(context).cancelAll()
    }

    fun clearNotificationsWithTag(context: Context, tag: String) {
        val notificationManager = getNotificationManager(context)
        notificationManager.activeNotifications.forEach { statusBarNotification ->
            if (statusBarNotification.tag == tag) {
                notificationManager.cancel(tag, statusBarNotification.id)
            }
        }
    }

    private fun getNotificationManager(context: Context) =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


}