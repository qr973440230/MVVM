package com.qr.library.mvvm.di.injector

import android.app.Application
import android.content.Context
import dagger.android.HasAndroidInjector
import dagger.internal.Preconditions

object InjectorUtils {

    fun inject(context: Context, target: Any) {
        val application = context.applicationContext as Application
        inject(application, target)
    }

    fun inject(application: Application, target: Any) {
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
        Preconditions.checkNotNull(
            androidInjector,
            "%s.androidInjector() returned null",
            application.javaClass
        )
        androidInjector.inject(target)
    }

}