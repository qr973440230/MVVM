package com.qr.demo.mvvm.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.alibaba.android.arouter.facade.annotation.Route
import com.qr.demo.mvvm.R
import com.qr.library.mvvm.livedata.throttleFirst
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.coroutines.delay
import timber.log.Timber
import java.util.concurrent.TimeUnit

class MainActivity : DaggerAppCompatActivity() {
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        liveData {
            for (i in 1..3) {
                emit(i)
                delay(10)
            }
        }.throttleFirst(1000,TimeUnit.MILLISECONDS).observe(this,Observer{
            Timber.d("$it")
        })
    }
}
