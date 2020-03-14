@file:Suppress("NOTHING_TO_INLINE")

package com.qr.library.mvvm.livedata

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.toPublisher
import io.reactivex.rxjava3.core.*
import org.reactivestreams.Publisher

inline fun <T> Publisher<T>.toObservable(): Observable<T> = Observable.fromPublisher(this)

inline fun <T> LiveData<T>.toObservable(lifecycleOwner: LifecycleOwner): Observable<T> =
    toPublisher(lifecycleOwner).toObservable()

inline fun <T> Publisher<T>.toFlowable(): Flowable<T> = Flowable.fromPublisher(this)

inline fun <T> LiveData<T>.toFlowable(lifecycleOwner: LifecycleOwner): Flowable<T> =
    toPublisher(lifecycleOwner).toFlowable()

inline fun <T> Publisher<T>.toSingle(): Single<T> = Single.fromPublisher(this)

inline fun <T> LiveData<T>.toSingle(lifecycleOwner: LifecycleOwner) =
    toPublisher(lifecycleOwner).toSingle()

inline fun <T> Publisher<T>.toMaybe(): Maybe<T> = Maybe.fromPublisher(this)

inline fun <T> LiveData<T>.toMaybe(lifecycleOwner: LifecycleOwner) =
    toPublisher(lifecycleOwner).toMaybe()

inline fun <T> Publisher<T>.toCompletable(): Completable = Completable.fromPublisher(this)

inline fun <T> LiveData<T>.toCompletable(lifecycleOwner: LifecycleOwner) =
    toPublisher(lifecycleOwner).toCompletable()
