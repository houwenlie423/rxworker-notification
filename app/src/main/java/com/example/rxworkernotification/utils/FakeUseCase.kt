package com.example.rxworkernotification.utils

import io.reactivex.Completable
import java.util.concurrent.TimeUnit


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version FakeUseCase, v 0.1 Thu 7/6/2023 4:59 PM by Houwen Lie
 */
object FakeUseCase {

    private const val MOCK_DELAY_IN_SECONDS = 3L

    // In real project these are UseCase<T> that do all sorts of complex data manipulations
    fun agree(param1: String, param2: String): Completable {
        return Completable
            .fromAction {
                LogUtil.log("Executing FakeUseCase.agree() in ${Thread.currentThread().name}")
                LogUtil.log("Param 1 = $param1, param 2 = $param2")
            }
            .delay(MOCK_DELAY_IN_SECONDS, TimeUnit.SECONDS)
            .doOnComplete { LogUtil.log("FakeUseCase.agree() Completed") }
    }

    fun reject(param1: String, param2: String): Completable {
        return Completable
            .fromAction {
                LogUtil.log("Executing FakeUseCase.reject() in ${Thread.currentThread().name}")
                LogUtil.log("Param 1 = $param1, param 2 = $param2")
            }
            .delay(MOCK_DELAY_IN_SECONDS, TimeUnit.SECONDS)
            .doOnComplete { LogUtil.log("FakeUseCase.reject() Completed") }
    }
}