package com.qr.demo.mvvm.ui

import android.app.Application
import android.content.SharedPreferences
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.qr.library.mvvm.cache.CacheManager
import com.qr.library.mvvm.viewmodel.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel(application: Application) : BaseViewModel(application) {
    @Inject
    lateinit var gson: Gson

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    @Inject
    lateinit var cacheManager: CacheManager

}