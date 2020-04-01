package com.qr.library.mvvm.paging.position

import androidx.lifecycle.MutableLiveData
import androidx.paging.PositionalDataSource
import com.qr.library.mvvm.repository.NetworkStatus
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

abstract class BasePositionalDataSource<Value> : PositionalDataSource<Value>() {
    private var _retry: (() -> Unit)? = null
    val networkStatus = MutableLiveData<NetworkStatus>()

    fun retry() {
        val preRetry = _retry
        _retry = null
        preRetry?.invoke()
    }

    @Throws(exceptionClasses = [Exception::class])
    protected abstract suspend fun loadInitImpl(
        params: LoadInitialParams,
        callback: LoadInitialCallback<Value>
    )

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Value>) {
        GlobalScope.launch {
            try {
                networkStatus.postValue(NetworkStatus.LOADING)
                loadInitImpl(params, callback)
                networkStatus.postValue(NetworkStatus.SUCCESS)
                _retry = null
            } catch (e: Exception) {
                networkStatus.postValue(NetworkStatus.error(e.message))
                _retry = { loadInitial(params, callback) }
            }
        }
    }

    @Throws(exceptionClasses = [Exception::class])
    protected abstract suspend fun loadRangeImpl(
        params: LoadRangeParams,
        callback: LoadRangeCallback<Value>
    )

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Value>) {
        GlobalScope.launch {
            try {
                networkStatus.postValue(NetworkStatus.LOADING)
                loadRangeImpl(params, callback)
                networkStatus.postValue(NetworkStatus.SUCCESS)
                _retry = null
            } catch (e: Exception) {
                networkStatus.postValue(NetworkStatus.error(e.message))
                _retry = { loadRange(params, callback) }
            }
        }
    }
}

