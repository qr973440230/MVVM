package com.qr.library.mvvm.cache

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.room.Room
import com.google.gson.Gson

class CacheManager(
    context: Context,
    val gson: Gson
) {
    val cacheDao =
        Room.databaseBuilder(
                context.applicationContext,
                CacheDatabase::class.java,
                "cache_database"
            )
            .allowMainThreadQueries()
            .build()
            .getCacheDao()

    fun <T> save(key: String, data: T) {
        val cache = Cache(key, gson.toJson(data))
        cacheDao.insert(cache)
    }

    suspend fun <T> saveAsync(key: String, data: T) {
        val cache = Cache(key, gson.toJson(data))
        cacheDao.insertAsync(cache)
    }

    inline fun <reified T> get(key: String): T? {
        val cache = cacheDao.getCache(key)
        return cache?.let { gson.fromJson(it, T::class.java) }
    }

    inline fun <reified T> getLiveData(key: String): LiveData<T> {
        return cacheDao.getCacheLiveData(key).map { gson.fromJson(it, T::class.java) }
    }

    suspend inline fun <reified T> getAsync(key: String): T? {
        val cache = cacheDao.getCacheAsync(key)
        return cache?.let { gson.fromJson(it, T::class.java) }
    }

}

