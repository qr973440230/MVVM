package com.qr.library.mvvm.fragment

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar


// Toast
fun Fragment.showShortToast(msg: String) =
    Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()

fun Fragment.showLongToast(msg: String) =
    Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()

// SnackBar
fun Fragment.shortSnackBar(msg: String): Snackbar =
    Snackbar.make(requireView(), msg, Snackbar.LENGTH_SHORT)

fun Fragment.longSnackBar(msg: String): Snackbar =
    Snackbar.make(requireView(), msg, Snackbar.LENGTH_LONG)

fun Fragment.indefiniteSnackBar(msg: String): Snackbar =
    Snackbar.make(requireView(), msg, Snackbar.LENGTH_INDEFINITE)

