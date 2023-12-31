package com.example.rxworkernotification.worker

import android.content.Context
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.RxWorker
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.example.rxworkernotification.notification.NotificationUtil
import com.example.rxworkernotification.utils.FakeUseCase
import com.example.rxworkernotification.utils.applySchedulers
import com.example.rxworkernotification.utils.subscribeByAutoDispose
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version VerificationWorker, v 0.1 Thu 7/6/2023 7:21 PM by Houwen Lie
 */
class VerificationWorker(
    private val context: Context,
    private val workerParameters: WorkerParameters
) : RxWorker(context, workerParameters) {

    override fun createWork(): Single<Result> {
        val agree = workerParameters.inputData.getBoolean(VerificationWorkerKeys.AGREE, false)
        val param1 = workerParameters.inputData.getString(VerificationWorkerKeys.PARAM_1).orEmpty()
        val param2 = workerParameters.inputData.getString(VerificationWorkerKeys.PARAM_2).orEmpty()

        return if (param1.isNotEmpty() && param2.isNotEmpty()) {
            val service = if (agree) FakeUseCase.agree(param1, param2) else FakeUseCase.reject(param1, param2)

            Single.create { emitter ->
                service
                    .applySchedulers()
                    .doOnSubscribe {
                        NotificationUtil.showNotification(
                            context = context,
                            title = "PLEASE WAIT",
                            message = "Loading...."
                        )
                    }
                    .subscribeByAutoDispose(
                        onComplete = {
                            emitter.onSuccess(Result.success())
                            NotificationUtil.showNotification(
                                context = context,
                                title = "Success bro",
                                message = "Ceritanya success"
                            )
                        },
                        onError = {
                            emitter.onSuccess(Result.failure())
                            NotificationUtil.showNotification(
                                context = context,
                                title = "WADUH ERROR COK",
                                message = "Error = ${it.message}"
                            )
                        }
                    )
            }
        } else {
            Single.just(Result.failure())
        }
    }

    companion object {

        fun start(
            context: Context,
            agree: Boolean,
            param1: String,
            param2: String
        ) {
            val inputData = Data.Builder()
                .putBoolean(VerificationWorkerKeys.AGREE, agree)
                .putString(VerificationWorkerKeys.PARAM_1, param1)
                .putString(VerificationWorkerKeys.PARAM_2, param2)
                .build()

            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

            val request = OneTimeWorkRequestBuilder<VerificationWorker>()
                .setInputData(inputData)
                .setConstraints(constraints)
                .build()

            WorkManager.getInstance(context.applicationContext).enqueue(request)
        }
    }
}