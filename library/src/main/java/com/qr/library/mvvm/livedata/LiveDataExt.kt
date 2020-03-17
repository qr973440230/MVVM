package com.qr.library.mvvm.livedata

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.toPublisher
import io.reactivex.rxjava3.core.*
import org.reactivestreams.Publisher

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
