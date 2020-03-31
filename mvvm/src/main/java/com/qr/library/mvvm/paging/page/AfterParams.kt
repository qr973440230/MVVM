package com.qr.library.mvvm.paging.page

import androidx.paging.PageKeyedDataSource

internal data class AfterParams<Key, Value>(
    val params: PageKeyedDataSource.LoadParams<Key>,
    val callback: PageKeyedDataSource.LoadCallback<Key, Value>
)