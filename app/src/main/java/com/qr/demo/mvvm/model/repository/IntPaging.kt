package com.qr.demo.mvvm.model.repository

import com.qr.library.mvvm.paging.page.BasePageKeyedDataSource

class IntPaging: BasePageKeyedDataSource<Int,Int>(){
    override suspend fun loadInitImpl(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Int>
    ) {

    }

    override suspend fun loadAfterImpl(params: LoadParams<Int>, callback: LoadCallback<Int, Int>) {

    }
}