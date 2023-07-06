package com.example.rxworkernotification.utils

import android.util.Log


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version LogUtil, v 0.1 Thu 7/6/2023 4:17 PM by Houwen Lie
 */
object LogUtil {

    private const val GLOBAL_TAG = "RxWorkerTag"

    fun log(message: String) {
        Log.d(GLOBAL_TAG, message)
    }
}