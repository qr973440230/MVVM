package com.qr.library.mvvm.application

import android.app.Activity
import android.os.Process
import java.util.*
import kotlin.system.exitProcess

object AppManager {
    private val activities: MutableList<Activity> = LinkedList()

    @Synchronized
    fun addActivity(activity: Activity) {
        if (!activities.contains(activity)) {
            activities.add(activity)
        }
    }

    @Synchronized
    fun removeActivity(activity: Activity) {
        activities.remove(activity)
    }

    @Synchronized
    fun removeActivity(location: Int): Activity? {
        if (location > 0 && location < activities.size) {
            return activities.removeAt(location)
        }
        return null
    }

    @Synchronized
    fun killActivity(activityClass: Class<*>) =
        activities.removeAll {
            if (it.javaClass == activityClass) {
                it.finish()
                true
            } else {
                false
            }
        }

    fun hasActivity(activity: Activity) = activities.contains(activity)
    fun hasActivity(activityClass: Class<*>) = activities.any { it.javaClass == activityClass }
    fun findFirstActivity(activityClass: Class<*>) =
        activities.firstOrNull { it.javaClass == activityClass }

    fun findAllActivity(activityClass: Class<*>) =
        activities.filter { it.javaClass == activityClass }

    @Synchronized
    fun killAll() {
        activities.removeAll {
            it.finish()
            true
        }
    }

    @Synchronized
    fun killAll(vararg excludeActivityClasses: Class<*>?) {
        activities.removeAll {
            if (excludeActivityClasses.contains(it.javaClass)) {
                false
            } else {
                it.finish()
                true
            }
        }
    }

    @Synchronized
    fun killAll(vararg excludeActivityName: String?) {
        activities.removeAll {
            if (excludeActivityName.contains(it.javaClass.name)) {
                false
            } else {
                it.finish()
                true
            }
        }
    }

    fun appExit() {
        try {
            killAll()
            Process.killProcess(Process.myPid())
            exitProcess(0)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}