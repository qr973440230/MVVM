package com.qr.library.mvvm.application

import android.app.Application
import com.qr.library.mvvm.di.injector.InjectorUtils

fun Application.inject(target: Any) = InjectorUtils.inject(this, target)
