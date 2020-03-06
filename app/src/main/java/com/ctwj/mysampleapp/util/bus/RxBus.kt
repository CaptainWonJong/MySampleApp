package com.ctwj.mysampleapp.util.bus

import android.annotation.SuppressLint
import io.reactivex.subjects.PublishSubject
import timber.log.Timber

/**
 * @author CaptainWonJong@gmail.com
 */
object RxBus {

    val subject = PublishSubject.create<Any>()

    fun sendEvent(item: Any) {
        subject.onNext(item)
    }

    @SuppressLint("CheckResult")
    private fun onError() {
        subject.doOnError { Timber.e(it.toString()) }
    }
}