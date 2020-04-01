package com.qr.library.mvvm.paging.page

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.qr.library.mvvm.repository.NetworkStatus
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

abstract class BasePageKeyedDataSource<Key, Value> : PageKeyedDataSource<Key, Value>() {
    private var _retry: (() -> Unit)? = null
    val networkStatus = MutableLiveData<NetworkStatus>()

    fun retry() {
        val preRetry = _retry
        _retry = null
        preRetry?.invoke()
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
    protected abstract suspend fun loadAfterImpl(
        params: LoadParams<Key>,
        callback: LoadCallback<Key, Value>
    )

    override fun loadAfter(params: LoadParams<Key>, callback: LoadCallback<Key, Value>) {
        GlobalScope.launch {
             try {
                networkStatus.postValue(NetworkStatus.LOADING)
                loadAfterImpl(params, callback)
                networkStatus.postValue(NetworkStatus.SUCCESS)
                _retry = null
            } catch (e: Exception) {
                networkStatus.postValue(NetworkStatus.error(e.message))
                _retry = { loadAfter(params, callback) }
            }
        }
    }

    override fun loadBefore(params: LoadParams<Key>, callback: LoadCallback<Key, Value>) {

    }

}