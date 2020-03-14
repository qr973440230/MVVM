package com.crsc.app.tram_app.config.log

import android.util.Log
import com.orhanobut.logger.Logger
import timber.log.Timber

class FileLoggingTree : Timber.DebugTree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority == Log.VERBOSE) {
            return;
        }

        Logger.log(priority, tag, message, t)
    }
}