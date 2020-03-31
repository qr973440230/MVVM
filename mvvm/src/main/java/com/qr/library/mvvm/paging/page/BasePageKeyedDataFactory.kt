package com.qr.library.mvvm.paging.page

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource

abstract class BasePageKeyedDataFactory<Key, Value> : DataSource.Factory<Key, Value>() {
    val dataSourceLiveData = MutableLiveData<BasePageKeyedDataSource<Key, Value>>()

    override fun create(): DataSource<Key, Value> {
        val dataSource = createDataSource()
        dataSourceLiveData.postValue(dataSource)
        return dataSource
    }

    abstract fun createDataSource(): BasePageKeyedDataSource<Key, Value>
}