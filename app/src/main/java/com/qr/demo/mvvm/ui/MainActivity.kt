package com.qr.demo.mvvm.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.liveData
import androidx.lifecycle.observe
import androidx.lifecycle.toPublisher
import com.qr.demo.mvvm.R
import com.qr.library.mvvm.livedata.throttleFirst
import com.qr.library.mvvm.livedata.toObservable
import dagger.android.support.DaggerAppCompatActivity
import java.util.concurrent.TimeUnit

class MainActivity : DaggerAppCompatActivity() {
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        liveData {
            emit(1)
        }.throttleFirst(1, TimeUnit.SECONDS)
            .observe(this) {

            }
    }
}
