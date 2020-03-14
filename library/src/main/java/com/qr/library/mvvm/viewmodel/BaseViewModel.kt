package com.qr.library.mvvm.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.qr.library.mvvm.di.injector.InjectorUtils
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

abstract class BaseViewModel(application: Application) : AndroidViewModel(application),
    HasAndroidInjector {

    init {
        application.let {
            InjectorUtils.inject(it, this)
        }
    }

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }

}