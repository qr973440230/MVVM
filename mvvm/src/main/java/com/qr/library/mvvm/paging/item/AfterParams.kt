package com.qr.library.mvvm.paging.item

import androidx.paging.ItemKeyedDataSource

internal data class AfterParams<Key, Value>(
    val params: ItemKeyedDataSource.LoadParams<Key>,
    val callback: ItemKeyedDataSource.LoadCallback<Value>
)