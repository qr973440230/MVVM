package com.qr.library.mvvm.context

import android.content.Context
import android.widget.Toast
import com.qr.library.mvvm.di.injector.InjectorUtils


// Toast
fun Context.showShortToast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

fun Context.showLongToast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_LONG).show()

fun Context.inject(target: Any) = InjectorUtils.inject(this, target)