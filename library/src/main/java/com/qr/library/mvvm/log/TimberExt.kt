package com.qr.library.mvvm.log

import timber.log.Timber

fun logD(msg: String) = Timber.d(msg)

fun logD(t: Throwable) = Timber.d(t)

fun logW(msg: String) = Timber.w(msg)

fun logW(t: Throwable) = Timber.w(t)

fun logE(msg: String) = Timber.e(msg)

fun logE(t: Throwable) = Timber.e(t)

fun logI(msg: String) = Timber.i(msg)

fun logI(t: Throwable) = Timber.i(t)
