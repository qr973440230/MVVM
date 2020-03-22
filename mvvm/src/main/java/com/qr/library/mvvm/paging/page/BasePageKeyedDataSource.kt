package com.qr.library.mvvm.paging.page

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.qr.library.mvvm.repository.NetworkStatus

abstract class BasePageKeyedDataSource<Key, Value> : PageKeyedDataSource<Key, Value>() {
    private var retry: (() -> Any)? = null

    val refreshStatus by lazy {
        MutableLiveData<NetworkStatus>()
    }

    val networkStatus by lazy {
        MutableLiveData<NetworkStatus>()
    }

    fun retryFailed() {
        val preRetry = retry
        retry = null
        preRetry?.invoke()
    }

    override fun loadInitial(
        params: LoadInitialParams<Key>,
        callback: LoadInitialCallback<Key, Value>
    ) {
        refreshStatus.postValue(NetworkStatus.LOADING)
        networkStatus.postValue(NetworkStatus.LOADING)
        setLoadInitial(params, callback)
    }

    fun loadInitialSuccess() {
        refreshStatus.postValue(NetworkStatus.SUCCESS)
        networkStatus.postValue(NetworkStatus.SUCCESS)
        retry = null
    }

    fun loadInitialFailed(
        msg: String?,
        params: LoadInitialParams<Key>,
        callback: LoadInitialCallback<Key, Value>
    ) {
        val error = NetworkStatus.error(msg)
        refreshStatus.postValue(error)
        networkStatus.postValue(error)
        retry = { loadInitial(params, callback) }
    }

    abstract fun setLoadInitial(
        params: LoadInitialParams<Key>,
        callback: LoadInitialCallback<Key, Value>
    )

    override fun loadAfter(params: LoadParams<Key>, callback: LoadCallback<Key, Value>) {
        networkStatus.postValue(NetworkStatus.LOADING)
        setLoadAfter(params, callback)
    }

    fun loadAfterSuccess() {
        networkStatus.postValue(NetworkStatus.SUCCESS)
        retry = null
    }

    fun loadAfterFailed(msg: String?, params: LoadParams<Key>, callback: LoadCallback<Key, Value>) {
        networkStatus.postValue(NetworkStatus.error(msg))
        retry = { loadAfter(params, callback) }
    }

    abstract fun setLoadAfter(params: LoadParams<Key>, callback: LoadCallback<Key, Value>)

    override fun loadBefore(params: LoadParams<Key>, callback: LoadCallback<Key, Value>) {
    }

}