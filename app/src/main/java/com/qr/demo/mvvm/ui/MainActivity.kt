package com.qr.demo.mvvm.ui

import android.os.Bundle
import androidx.activity.viewModels
import com.qr.demo.mvvm.R
import dagger.android.support.DaggerAppCompatActivity
import dagger.android.support.DaggerFragment
import timber.log.Timber

class MainActivity : DaggerAppCompatActivity() {
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Timber.d(mainViewModel.gsonString)
    }
}
