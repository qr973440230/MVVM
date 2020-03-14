package com.qr.library.mvvm.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

abstract class BaseViewModel(application: Application) : AndroidViewModel(application),
    HasAndroidInjector {

    init {
        inject(application)
    }

    private fun inject(application: Application) {
        if (application !is HasAndroidInjector) {
            throw RuntimeException(
                String.format(
                    "%s does not implement %s",
                    application::class.java.canonicalName,
                    HasAndroidInjector::class.java.canonicalName
                )
            );
        }
        val androidInjector = application.androidInjector()
        androidInjector.inject(this)
    }

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }

}