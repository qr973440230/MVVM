package com.qr.library.mvvm.repository.paging.position

import androidx.lifecycle.MutableLiveData
import androidx.paging.PositionalDataSource
import com.qr.library.mvvm.repository.NetworkStatus

abstract class BasePositionalDataSource<Value> : PositionalDataSource<Value>() {
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


    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Value>) {

    }

    fun loadInitialSuccess() {
        refreshStatus.postValue(NetworkStatus.SUCCESS)
        networkStatus.postValue(NetworkStatus.SUCCESS)
        retry = null
    }

    fun loadInitialFailed(
        msg: String?,
        params: LoadInitialParams,
        callback: LoadInitialCallback<Value>
    ) {
        val error = NetworkStatus.error(msg)
        refreshStatus.postValue(error)
        networkStatus.postValue(error)
        retry = { loadInitial(params, callback) }
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Value>) {

    }

    fun loadRangeSuccess() {
        networkStatus.postValue(NetworkStatus.SUCCESS)
        retry = null
    }

    fun loadRangeFailed(msg: String?, params: LoadRangeParams, callback: LoadRangeCallback<Value>) {
        networkStatus.postValue(NetworkStatus.error(msg))
        retry = { loadRange(params, callback) }
    }

    abstract fun setLoadInitial(params: LoadRangeParams, callback: LoadRangeCallback<Value>)

    abstract fun setLoadRange(params: LoadRangeParams, callback: LoadRangeCallback<Value>)
}
