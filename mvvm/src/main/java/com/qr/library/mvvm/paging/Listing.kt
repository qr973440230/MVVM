package com.qr.library.mvvm.paging

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.qr.library.mvvm.repository.NetworkStatus


data class Listing<T>(
    val pagedList: LiveData<PagedList<T>>,
    val networkStatus: LiveData<NetworkStatus>,
    val refresh: () -> Unit,
    val retry: () -> Unit
)
