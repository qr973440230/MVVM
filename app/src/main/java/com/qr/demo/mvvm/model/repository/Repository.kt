package com.qr.demo.mvvm.model.repository

import androidx.lifecycle.switchMap
import androidx.paging.toLiveData
import com.qr.library.mvvm.paging.Listing
import com.qr.library.mvvm.paging.page.BasePageKeyedDataFactory
import com.qr.library.mvvm.paging.page.BasePageKeyedDataSource

class Repository {
    fun getListing(): Listing<Int> {
        val factory = object : BasePageKeyedDataFactory<Int, Int>() {
            override fun createDataSource(): BasePageKeyedDataSource<Int, Int> {
                return IntPaging()
            }
        }

        return Listing(
            factory.toLiveData(20),
            factory.dataSourceLiveData.switchMap { it.networkStatus },
            { factory.dataSourceLiveData.value?.invalidate() },
            { factory.dataSourceLiveData.value?.retry() }
        )
    }
}