package com.qr.library.mvvm.livedata

import androidx.lifecycle.*
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.delayFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.sample
import java.util.concurrent.TimeUnit

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
    val durationMills = unit.toMillis(duration)
    return asFlow().debounce(durationMills).asLiveData()
}