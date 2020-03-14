package com.qr.demo.mvvm.ui

import android.app.Application
import android.content.SharedPreferences
import com.google.gson.Gson
import com.qr.library.mvvm.viewmodel.BaseViewModel
import javax.inject.Inject

class MainViewModel(application: Application) : BaseViewModel(application) {
    @Inject
    lateinit var gson: Gson

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    val gsonString = gson.toString()

}