package com.qr.library.mvvm.paging.item

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource

abstract class BaseItemKeyedDataFactory<Key, Value> : DataSource.Factory<Key, Value>() {
    val dataSourceLiveData = MutableLiveData<BaseItemKeyedDataSource<Key, Value>>()

    override fun create(): DataSource<Key, Value> {
        val dataSource = createDataSource()
        dataSourceLiveData.postValue(dataSource)
        return dataSource
    }

    protected abstract fun createDataSource(): BaseItemKeyedDataSource<Key, Value>
}