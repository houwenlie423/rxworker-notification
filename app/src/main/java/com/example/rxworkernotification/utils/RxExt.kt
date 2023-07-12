package com.example.rxworkernotification.utils

import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version RxExt, v 0.1 Wed 7/12/2023 11:05 PM by Houwen Lie
 */

fun Completable.applySchedulers(
    threadScheduler: Scheduler = Schedulers.io(),
    postThreadScheduler: Scheduler = AndroidSchedulers.mainThread()
) = this.subscribeOn(threadScheduler).observeOn(postThreadScheduler)

fun Completable.subscribeByAutoDispose(
    onComplete: () -> Unit,
    onError: (Throwable) -> Unit
) {
    this.subscribe(object : DisposableCompletableObserver() {
        override fun onComplete() {
            onComplete()
            dispose()
        }

        override fun onError(e: Throwable) {
            onError(e)
            dispose()
        }
    })
}