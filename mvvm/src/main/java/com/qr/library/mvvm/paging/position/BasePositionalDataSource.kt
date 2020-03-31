package com.qr.library.mvvm.paging.position

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.paging.PositionalDataSource
import com.qr.library.mvvm.repository.NetworkStatus

abstract class BasePositionalDataSource<Value> : PositionalDataSource<Value>() {
    private var _retry: (() -> Unit)? = null

    val networkStatus = MediatorLiveData<NetworkStatus>().apply {
        addSource(initStatus) {
            if (value != it) {
                value = it
            }
        }
        addSource(rangeStatus) {
            if (value != it) {
                value = it
            }
        }
    }

    fun retry() {
        val preRetry = _retry
        _retry = null
        preRetry?.invoke()
    }

    private val initParams = MutableLiveData<InitParams<Value>>()
    private val initStatus = initParams.switchMap {
        liveData {
            emit(NetworkStatus.LOADING)
            try {
                loadInitImpl(it.params, it.callback)
                emit(NetworkStatus.SUCCESS)
                _retry = null
            } catch (e: Exception) {
                it.callback.onError(e)
                emit(NetworkStatus.error(e.message))
                _retry = { initParams.postValue(it) }
            }
        }
    }

    @Throws(exceptionClasses = [Exception::class])
    protected abstract suspend fun loadInitImpl(
        params: LoadInitialParams,
        callback: LoadInitialCallback<Value>
    )

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Value>) {
        initParams.postValue(InitParams(params, callback))
    }

    private val rangeParams = MutableLiveData<RangeParams<Value>>()
    private val rangeStatus = rangeParams.switchMap {
        liveData {
            emit(NetworkStatus.LOADING)
            try {
                loadRangeImpl(it.params, it.callback)
                emit(NetworkStatus.SUCCESS)
                _retry = null
            } catch (e: Exception) {
                it.callback.onError(e)
                emit(NetworkStatus.error(e.message))
                _retry = { rangeParams.postValue(it) }
            }
        }
    }

    @Throws(exceptionClasses = [Exception::class])
    protected abstract suspend fun loadRangeImpl(
        params: LoadRangeParams,
        callback: LoadRangeCallback<Value>
    )

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Value>) {
        rangeParams.postValue(RangeParams(params, callback))
    }
}

