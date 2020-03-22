package com.qr.library.mvvm.paging.page

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource

abstract class BasePageKeyedDataSourceFactory<Key, Value> : DataSource.Factory<Key, Value>() {

    private val sourceLivaData = MutableLiveData<BasePageKeyedDataSource<Key, Value>>()

    override fun create(): DataSource<Key, Value> {
        val dataSource = createDataSource()
        sourceLivaData.postValue(dataSource)
        return dataSource
    }

    abstract fun createDataSource(): BasePageKeyedDataSource<Key, Value>
}