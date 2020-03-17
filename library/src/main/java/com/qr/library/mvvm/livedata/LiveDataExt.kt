package com.qr.library.mvvm.livedata

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.toPublisher
import io.reactivex.rxjava3.core.*
import org.reactivestreams.Publisher
import java.util.concurrent.TimeUnit

fun <T> Publisher<T>.toObservable(): Observable<T> = Observable.fromPublisher(this)

fun <T> LiveData<T>.toObservable(lifecycleOwner: LifecycleOwner): Observable<T> =
    toPublisher(lifecycleOwner).toObservable()

fun <T> Publisher<T>.toFlowable(): Flowable<T> = Flowable.fromPublisher(this)

fun <T> LiveData<T>.toFlowable(lifecycleOwner: LifecycleOwner): Flowable<T> =
    toPublisher(lifecycleOwner).toFlowable()

fun <T> Publisher<T>.toSingle(): Single<T> = Single.fromPublisher(this)

fun <T> LiveData<T>.toSingle(lifecycleOwner: LifecycleOwner) =
    toPublisher(lifecycleOwner).toSingle()

fun <T> Publisher<T>.toMaybe(): Maybe<T> = Maybe.fromPublisher(this)

fun <T> LiveData<T>.toMaybe(lifecycleOwner: LifecycleOwner) =
    toPublisher(lifecycleOwner).toMaybe()

fun <T> Publisher<T>.toCompletable(): Completable = Completable.fromPublisher(this)

fun <T> LiveData<T>.toCompletable(lifecycleOwner: LifecycleOwner) =
    toPublisher(lifecycleOwner).toCompletable()

fun <T> LiveData<T>.throttleFirst(duration: Long, unit: TimeUnit): LiveData<T> {
    val outputLiveData = MediatorLiveData<T>()
    outputLiveData.addSource(this, object : Observer<T> {
        val durationMills = unit.toMillis(duration)
        var lastUpdateTime: Long = 0
        override fun onChanged(currentValue: T) {
            val currentTime = System.currentTimeMillis()
            if (currentTime - durationMills > lastUpdateTime) {
                // 过了当前时间就发送第一个值
                lastUpdateTime = currentTime
                outputLiveData.value = currentValue
            }
        }
    })
    return outputLiveData
}

fun <T> LiveData<T>.throttleLast(duration: Long, unit: TimeUnit): LiveData<T> {
    val outputLiveData = MediatorLiveData<T>()
    outputLiveData.addSource(this, object : Observer<T> {
        val durationMills = unit.toMillis(duration)
        val counting = false
        var lastValue: T? = null
        override fun onChanged(currentValue: T) {
            lastValue = currentValue
            if(counting){

            }
        }
    })
    return outputLiveData
}