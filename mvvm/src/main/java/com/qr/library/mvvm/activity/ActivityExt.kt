package com.qr.library.mvvm.activity

import android.app.Activity
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar


// Toast
fun Activity.showShortToast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

fun Activity.showLongToast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_LONG).show()

// SnackBar
fun Activity.shortSnackBar(msg: String): Snackbar =
    Snackbar.make(findViewById(android.R.id.content), msg, Snackbar.LENGTH_SHORT)

fun Activity.longSnackBar(msg: String): Snackbar =
    Snackbar.make(findViewById(android.R.id.content), msg, Snackbar.LENGTH_LONG)

fun Activity.indefiniteSnackBar(msg: String): Snackbar =
    Snackbar.make(findViewById(android.R.id.content), msg, Snackbar.LENGTH_INDEFINITE)

