package com.qr.library.mvvm.repository

import androidx.lifecycle.LiveData

data class Action(
    val status: LiveData<NetworkStatus>,
    val retry: (suspend () -> Unit)
)