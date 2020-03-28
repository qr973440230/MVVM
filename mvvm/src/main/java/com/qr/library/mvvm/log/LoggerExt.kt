@file:Suppress("NOTHING_TO_INLINE")

package com.qr.library.mvvm.log

import com.orhanobut.logger.Logger

inline fun Any.logV(msg: String): Unit =
    Logger.log(Logger.VERBOSE, this::class.java.name, msg, null)

inline fun Any.logV(t: Throwable): Unit =
    Logger.log(Logger.VERBOSE, this::class.java.name, null, t)

inline fun Any.logD(msg: String): Unit =
    Logger.log(Logger.DEBUG, this::class.java.name, msg, null)

inline fun Any.logD(t: Throwable): Unit =
    Logger.log(Logger.DEBUG, this::class.java.name, null, t)

inline fun Any.logI(msg: String): Unit =
    Logger.log(Logger.INFO, this::class.java.name, msg, null)

inline fun Any.logI(t: Throwable): Unit =
    Logger.log(Logger.INFO, this::class.java.name, null, t)

inline fun Any.logW(msg: String): Unit =
    Logger.log(Logger.WARN, this::class.java.name, msg, null)

inline fun Any.logW(t: Throwable): Unit =
    Logger.log(Logger.WARN, this::class.java.name, null, t)

inline fun Any.logE(msg: String): Unit =
    Logger.log(Logger.ERROR, this::class.java.name, msg, null)

inline fun Any.logE(t: Throwable): Unit =
    Logger.log(Logger.ERROR, this::class.java.name, null, t)

