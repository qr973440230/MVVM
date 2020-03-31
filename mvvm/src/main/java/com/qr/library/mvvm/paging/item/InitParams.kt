package com.qr.library.mvvm.paging.item

import androidx.paging.ItemKeyedDataSource

internal data class InitParams<Key, Value>(
    val params: ItemKeyedDataSource.LoadInitialParams<Key>,
    val callback: ItemKeyedDataSource.LoadInitialCallback<Value>
)