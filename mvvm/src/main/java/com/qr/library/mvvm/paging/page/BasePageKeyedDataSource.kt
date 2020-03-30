package com.qr.library.mvvm.paging.page

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.paging.PageKeyedDataSource
import com.qr.library.mvvm.repository.NetworkStatus

abstract class BasePageKeyedDataSource<Key, Value> : PageKeyedDataSource<Key, Value>() {
    private var _retry: (() -> Unit)? = null
    val networkStatus by lazy {
        MediatorLiveData<NetworkStatus>().apply {
            addSource(loadInitStatus) {
                if (value != it) {
                    value = it
                }
            }

            addSource(loadAfterStatus) {
                if (value != it) {
                    value = it
                }
            }
        }
    }

    fun retry() {
        val preRetry = _retry
        _retry = null
        preRetry?.invoke()
    }


    private val loadInitParams = MutableLiveData<LoadInitParams<Key, Value>>()
    private val loadInitStatus = loadInitParams.switchMap {
        liveData {
            emit(NetworkStatus.LOADING)
            try {
                loadInitImpl(it.params, it.callback)
                emit(NetworkStatus.SUCCESS)
                _retry = null
            } catch (e: Exception) {
                emit(NetworkStatus.error(e.message))
                _retry = ({ loadInitParams.postValue(it) })
            }
        }
    }

    @Throws(exceptionClasses = [Exception::class])
    abstract suspend fun loadInitImpl(
        params: LoadInitialParams<Key>,
        callback: LoadInitialCallback<Key, Value>
    )

    override fun loadInitial(
        params: LoadInitialParams<Key>,
        callback: LoadInitialCallback<Key, Value>
    ) {
        loadInitParams.postValue(LoadInitParams(params, callback))
    }

    private val loadAfterParams = MutableLiveData<LoadAfterParams<Key, Value>>()
    private val loadAfterStatus = loadAfterParams.switchMap {
        liveData {
            emit(NetworkStatus.LOADING)
            try {
                loadAfterImpl(it.params, it.callback)
                emit(NetworkStatus.SUCCESS)
                _retry = null
            } catch (e: Exception) {
                emit(NetworkStatus.error(e.message))
                _retry = ({ loadAfterParams.postValue(it) })
            }
        }
    }

    @Throws(exceptionClasses = [Exception::class])
    abstract suspend fun loadAfterImpl(params: LoadParams<Key>, callback: LoadCallback<Key, Value>)

    override fun loadAfter(params: LoadParams<Key>, callback: LoadCallback<Key, Value>) {
        loadAfterParams.postValue(LoadAfterParams(params, callback))
    }

    override fun loadBefore(params: LoadParams<Key>, callback: LoadCallback<Key, Value>) {
    }
}