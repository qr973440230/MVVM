package com.qr.library.mvvm.paging.position

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource

abstract class BasePositionalDataFactory<Value> : DataSource.Factory<Int, Value>() {
    private val dataSourceLiveData = MutableLiveData<BasePositionalDataSource<Value>>()

    override fun create(): DataSource<Int, Value> {
        val dataSource = createDataSource()
        dataSourceLiveData.postValue(dataSource)
        return dataSource
    }

    protected abstract fun createDataSource(): BasePositionalDataSource<Value>
}