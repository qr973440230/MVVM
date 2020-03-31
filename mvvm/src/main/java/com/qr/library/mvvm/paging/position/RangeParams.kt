package com.qr.library.mvvm.paging.position

import androidx.paging.PositionalDataSource

internal data class RangeParams<Value>(
    val params: PositionalDataSource.LoadRangeParams,
    val callback: PositionalDataSource.LoadRangeCallback<Value>
)