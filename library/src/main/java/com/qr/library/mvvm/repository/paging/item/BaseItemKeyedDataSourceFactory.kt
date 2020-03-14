package com.qr.library.mvvm.repository.paging.item

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource


abstract class BaseItemKeyedDataSourceFactory<Key, Value> : DataSource.Factory<Key, Value>() {

    private val sourceLivaData = MutableLiveData<BaseItemKeyedDataSource<Key, Value>>()

    override fun create(): BaseItemKeyedDataSource<Key, Value> {
        val keyedDataSource: BaseItemKeyedDataSource<Key, Value> = createDataSource()
        sourceLivaData.postValue(keyedDataSource)
        return keyedDataSource
    }

    abstract fun createDataSource(): BaseItemKeyedDataSource<Key, Value>
}