package com.qr.demo.mvvm

import android.content.Context
import androidx.multidex.MultiDex
import com.qr.demo.mvvm.di.components.DaggerApplicationComponent
import com.qr.library.mvvm.application.BaseApplication
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class APP : BaseApplication() {
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.factory().create(this)
    }
}