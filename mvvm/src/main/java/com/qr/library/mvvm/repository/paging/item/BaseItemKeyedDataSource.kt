package com.qr.library.mvvm.repository.paging.item

import androidx.lifecycle.MutableLiveData
import androidx.paging.ItemKeyedDataSource
import com.qr.library.mvvm.repository.NetworkStatus
import kotlinx.coroutines.Dispatchers


abstract class BaseItemKeyedDataSource<Key, Value> : ItemKeyedDataSource<Key, Value>() {
    private var retry: (() -> Any)? = null

    val networkStatus by lazy {
        MutableLiveData<NetworkStatus>()
    }

    val refreshStatus by lazy {
        MutableLiveData<NetworkStatus>()
    }

    fun retryFailed() {
        val preRetry = retry
        retry = null
        preRetry?.invoke()
    }

    override fun loadInitial(params: LoadInitialParams<Key>, callback: LoadInitialCallback<Value>) {
        refreshStatus.postValue(NetworkStatus.LOADING)
        networkStatus.postValue(NetworkStatus.LOADING)
        setLoadInitial(params, callback)
    }

    override fun loadAfter(params: LoadParams<Key>, callback: LoadCallback<Value>) {
        networkStatus.postValue(NetworkStatus.LOADING)
        setLoadAfter(params, callback)
    }

    override fun loadBefore(params: LoadParams<Key>, callback: LoadCallback<Value>) {
    }

    fun loadInitialSuccess() {
        refreshStatus.postValue(NetworkStatus.SUCCESS)
        networkStatus.postValue(NetworkStatus.SUCCESS)
        retry = null
    }

    fun loadInitialFailed(
        msg: String?,
        params: LoadInitialParams<Key>,
        callback: LoadInitialCallback<Value>
    ) {
        val error = NetworkStatus.error(msg)
        refreshStatus.postValue(error)
        networkStatus.postValue(error)
        retry = { loadInitial(params, callback) }
    }

    fun loadAfterSuccess() {
        networkStatus.postValue(NetworkStatus.SUCCESS)
        retry = null
    }

    fun loadAfterFailed(msg: String?, params: LoadParams<Key>, callback: LoadCallback<Value>) {
        networkStatus.postValue(NetworkStatus.error(msg))
        retry = { loadAfter(params, callback) }
    }

    abstract fun setLoadInitial(
        params: LoadInitialParams<Key>,
        callback: LoadInitialCallback<Value>
    )

    abstract fun setLoadAfter(params: LoadParams<Key>, callback: LoadCallback<Value>)
}


