package com.qr.library.mvvm.repository

import androidx.lifecycle.LiveData

data class Resource<T>(
    val data: LiveData<T>,
    val status: LiveData<NetworkStatus>,
    val retry: (suspend () -> Unit)
)