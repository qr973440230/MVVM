package com.qr.library.mvvm.view

import android.view.View
import com.google.android.material.snackbar.Snackbar


// SnackBar
fun View.shortSnackBar(msg: String): Snackbar =
    Snackbar.make(this, msg, Snackbar.LENGTH_SHORT)

fun View.longSnackBar(msg: String): Snackbar =
    Snackbar.make(this, msg, Snackbar.LENGTH_LONG)

fun View.indefiniteSnackBar(msg: String): Snackbar =
    Snackbar.make(this, msg, Snackbar.LENGTH_INDEFINITE)
