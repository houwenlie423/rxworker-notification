package com.example.rxworkernotification.receiver

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.work.WorkManager
import com.example.rxworkernotification.notification.NotificationUtil
import com.example.rxworkernotification.worker.VerificationWorker
import com.example.rxworkernotification.worker.VerificationWorkerKeys


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version VerificationBroadcastReceiver, v 0.1 Thu 7/6/2023 7:59 PM by Houwen Lie
 */
class VerificationBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val agree = intent?.getBooleanExtra(VerificationWorkerKeys.AGREE, false) ?: false
        val param1 = intent?.getStringExtra(VerificationWorkerKeys.PARAM_1).orEmpty()
        val param2 = intent?.getStringExtra(VerificationWorkerKeys.PARAM_2).orEmpty()


        if (context != null && param1.isNotEmpty() && param2.isNotEmpty()) {
            VerificationWorker.start(context, agree, param1, param2)
        }
    }
}