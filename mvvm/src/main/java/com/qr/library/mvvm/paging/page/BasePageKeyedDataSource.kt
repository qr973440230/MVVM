package com.qr.library.mvvm.paging.page

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.paging.PageKeyedDataSource
import com.qr.library.mvvm.repository.NetworkStatus

abstract class BasePageKeyedDataSource<Key, Value> : PageKeyedDataSource<Key, Value>() {
    private var _retry: (() -> Unit)? = null

    private val initParams = MutableLiveData<InitParams<Key, Value>>()
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
                _retry = ({ initParams.postValue(it) })
            }
        }
    }

    @Throws(exceptionClasses = [Exception::class])
    protected abstract suspend fun loadInitImpl(
        params: LoadInitialParams<Key>,
        callback: LoadInitialCallback<Key, Value>
    )

    override fun loadInitial(
        params: LoadInitialParams<Key>,
        callback: LoadInitialCallback<Key, Value>
    ) {
        initParams.postValue(InitParams(params, callback))
    }

    private val afterParams = MutableLiveData<AfterParams<Key, Value>>()
    private val afterStatus = afterParams.switchMap {
        liveData {
            emit(NetworkStatus.LOADING)
            try {
                loadAfterImpl(it.params, it.callback)
                emit(NetworkStatus.SUCCESS)
                _retry = null
            } catch (e: Exception) {
                it.callback.onError(e)
                emit(NetworkStatus.error(e.message))
                _retry = ({ afterParams.postValue(it) })
            }
        }
    }

    @Throws(exceptionClasses = [Exception::class])
    protected abstract suspend fun loadAfterImpl(
        params: LoadParams<Key>,
        callback: LoadCallback<Key, Value>
    )

    override fun loadAfter(params: LoadParams<Key>, callback: LoadCallback<Key, Value>) {
        afterParams.postValue(AfterParams(params, callback))
    }

    override fun loadBefore(params: LoadParams<Key>, callback: LoadCallback<Key, Value>) {
    }

    val networkStatus = MediatorLiveData<NetworkStatus>().apply {
        addSource(initStatus) {
            if (value != it) {
                value = it
            }
        }

        addSource(afterStatus) {
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
}