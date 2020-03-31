package com.qr.library.mvvm.paging.position

import androidx.paging.PositionalDataSource

internal data class InitParams<Value>(
    val params: PositionalDataSource.LoadInitialParams,
    val callback: PositionalDataSource.LoadInitialCallback<Value>
)