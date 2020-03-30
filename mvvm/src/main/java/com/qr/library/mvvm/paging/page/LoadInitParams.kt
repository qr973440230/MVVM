package com.qr.library.mvvm.paging.page

import androidx.paging.PageKeyedDataSource

internal data class LoadInitParams<Key, Value>(
    val params: PageKeyedDataSource.LoadInitialParams<Key>,
    val callback: PageKeyedDataSource.LoadInitialCallback<Key, Value>
)