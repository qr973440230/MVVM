package com.qr.library.mvvm.application

import android.app.Activity
import android.os.Bundle
import com.crsc.app.tram_app.config.log.FileLoggingTree
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.DiskLogAdapter
import com.orhanobut.logger.Logger
import com.qr.library.mvvm.BuildConfig
import dagger.android.support.DaggerApplication
import timber.log.Timber


abstract class BaseApplication : DaggerApplication() {
    override fun onCreate() {
        super.onCreate()

        // Log Init
        if (BuildConfig.DEBUG) {
            Logger.addLogAdapter(AndroidLogAdapter())
        } else {
            Logger.addLogAdapter(DiskLogAdapter())
        }
        Timber.plant(FileLoggingTree())

        // APPManager Init
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                Timber.d("${activity.javaClass.name} : OnCreated")
                AppManager.addActivity(activity)
            }

            override fun onActivityDestroyed(activity: Activity) {
                Timber.d("${activity.javaClass.name} : OnDestroy")
                AppManager.removeActivity(activity)
            }

            override fun onActivityStarted(activity: Activity) {

            }

            override fun onActivityStopped(activity: Activity) {

            }


            override fun onActivityResumed(activity: Activity) {

            }

            override fun onActivityPaused(activity: Activity) {

            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

            }
        })
    }
}