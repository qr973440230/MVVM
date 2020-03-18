package com.qr.library.mvvm.cache

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.qr.library.mvvm.db.dao.BaseDao

@Dao
interface CacheDao : BaseDao<Cache> {
    @Query("select data from Cache where `key` =:key")
    fun getCache(key: String): String?

    @Query("select data from Cache where `key` =:key")
    fun getCacheLiveData(key: String): LiveData<String>

    @Query("select data from Cache where `key` =:key")
    suspend fun getCacheAsync(key: String): String?
}