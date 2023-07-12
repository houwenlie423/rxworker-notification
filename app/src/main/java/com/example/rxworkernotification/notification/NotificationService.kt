package com.example.rxworkernotification.notification

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.rxworkernotification.R
import com.example.rxworkernotification.SecondaryActivity
import com.example.rxworkernotification.receiver.VerificationBroadcastReceiver
import com.example.rxworkernotification.worker.VerificationWorkerKeys
import kotlin.random.Random


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version NotificationService, v 0.1 Thu 7/6/2023 4:00 PM by Houwen Lie
 */
object NotificationService {

    private val pendingIntentFlags by lazy {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_CANCEL_CURRENT
        } else {
            PendingIntent.FLAG_CANCEL_CURRENT
        }
    }
    fun showVerificationNotification(context: Context) {
        // Intent that gets fired off when "notification" itself is clicked
        val contentPendingIntent = PendingIntent.getActivity(
            context,
            Random.nextInt(),
            Intent(context, SecondaryActivity::class.java),
            pendingIntentFlags
        )


        // Intent that gets fired when user clicks "Agree"
        val agreePendingIntent = PendingIntent.getBroadcast(
            context,
            Random.nextInt(),
            Intent(context, VerificationBroadcastReceiver::class.java).apply {
                putExtra(VerificationWorkerKeys.AGREE, true)
                putExtra(VerificationWorkerKeys.PARAM_1, "Param 1")
                putExtra(VerificationWorkerKeys.PARAM_2, "Param 2")
            },
            pendingIntentFlags
        )

        val agreeAction = NotificationCompat.Action.Builder(
            R.drawable.ic_sport_kabaddi,
            "Agree",
            agreePendingIntent
        ).build()


        // Intent that gets fired when user clicks "Reject"
        val rejectPendingIntent = PendingIntent.getBroadcast(
            context,
            Random.nextInt(),
            Intent(context, VerificationBroadcastReceiver::class.java).apply {
                putExtra(VerificationWorkerKeys.AGREE, false)
                putExtra(VerificationWorkerKeys.PARAM_1, "Param 1")
                putExtra(VerificationWorkerKeys.PARAM_2, "Param 2")
            },
            pendingIntentFlags
        )
        val rejectAction = NotificationCompat.Action.Builder(
            R.drawable.ic_sport_kabaddi,
            "Reject",
            rejectPendingIntent // Change this to actual Intent that executes WorkManager
        ).build()


        // Show Final Notification
        NotificationUtil.showNotification(
            context = context,
            title = "Login Request from TixID",
            message = "Do you want to verify this request?",
            smallIcon = R.drawable.ic_sport_kabaddi,
            contentIntent = contentPendingIntent,
            actions = listOf(agreeAction, rejectAction),
        )

    }

    fun showCounterNotification() {}
}