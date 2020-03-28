package com.qr.library.mvvm.provider

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.pm.ApplicationInfo
import android.os.Bundle
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.DiskLogAdapter
import com.orhanobut.logger.Logger
import com.qr.library.mvvm.application.AppManager
import com.qr.library.mvvm.log.logD
import com.tencent.mmkv.MMKV

object MVVMInit {
    lateinit var application: Application

    fun init(context: Context?) {
        application = context as? Application ?: throw RuntimeException("mvvm init error!!!")

        // Log Init
        if (isDebug(application)) {
            Logger.addLogAdapter(object : AndroidLogAdapter() {
                override fun isLoggable(priority: Int, tag: String?) = priority >= Logger.DEBUG
            })
        } else {
            Logger.addLogAdapter(object : DiskLogAdapter() {
                override fun isLoggable(priority: Int, tag: String?) = priority >= Logger.INFO
            })
        }

        // MMKV init
        MMKV.initialize(application)

        application.registerActivityLifecycleCallbacks(object :
            Application.ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                AppManager.addActivity(activity)
                logD("${activity.javaClass.name}: OnCreated")
            }

            override fun onActivityDestroyed(activity: Activity) {
                AppManager.removeActivity(activity)
                logD("${activity.javaClass.name}: OnDestroyed")
            }

            override fun onActivityStarted(activity: Activity) {
                logD("${activity.javaClass.name}: OnStarted")
            }

            override fun onActivityStopped(activity: Activity) {
                logD("${activity.javaClass.name}: OnStopped")
            }

            override fun onActivityResumed(activity: Activity) {
                logD("${activity.javaClass.name}: OnResumed")
            }

            override fun onActivityPaused(activity: Activity) {
                logD("${activity.javaClass.name}: OnPaused")
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
                logD("${activity.javaClass.name}: OnSaveInstanceState")
            }
        })
    }


    private fun isDebug(application: Application): Boolean {
        return application.applicationInfo.flags.and(ApplicationInfo.FLAG_DEBUGGABLE) != 0
    }
}