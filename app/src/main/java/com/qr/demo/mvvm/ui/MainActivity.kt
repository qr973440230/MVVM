package com.qr.demo.mvvm.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.edit
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.qr.demo.mvvm.R
import com.qr.demo.mvvm.model.repository.Repository
import com.qr.library.mvvm.livedata.throttleFirst
import com.qr.library.mvvm.log.logD
import com.qr.library.mvvm.log.logV
import com.qr.library.mvvm.shared_preferences.sharedPreferences
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.coroutines.delay
import java.util.concurrent.TimeUnit

class MainActivity : DaggerAppCompatActivity() {
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences.edit(commit = true) {
            putString("1", "3")
        }

        liveData {
            for (i in 1..3) {
                emit(i)
                delay(10)
            }
        }.throttleFirst(1000, TimeUnit.MILLISECONDS).observe(this, Observer {
            val str = sharedPreferences.getString("1", null)
            if (str != null) {
                logD(str)
                logV(str)
            }
            sharedPreferences.edit().remove("1").apply()
        })

        Repository().getListing().pagedList.observe(this, Observer {
            logD(it.toString())
        })
    }
}
