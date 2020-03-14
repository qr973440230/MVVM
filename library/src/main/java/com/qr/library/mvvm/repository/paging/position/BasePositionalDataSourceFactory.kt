package com.qr.library.mvvm.repository.paging.position

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource


abstract class BasePositionalDataSourceFactory<Value> : DataSource.Factory<Int, Value>() {

    private val sourceLivaData = MutableLiveData<BasePositionalDataSource<Value>>()

    override fun create(): DataSource<Int, Value> {
        val dataSource = createDataSource()
        sourceLivaData.postValue(dataSource)
        return dataSource
    }

    abstract fun createDataSource(): BasePositionalDataSource<Value>
}