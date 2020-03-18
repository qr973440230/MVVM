package com.qr.library.mvvm.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.qr.library.mvvm.application.inject
import com.qr.library.mvvm.di.injector.InjectorUtils
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@Suppress("LeakingThis")
abstract class BaseViewModel(application: Application) : AndroidViewModel(application),
    HasAndroidInjector {

    init {
        let {
            application.inject(it)
        }
    }

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }
}