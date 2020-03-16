package com.qr.library.mvvm.cache

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Cache::class], version = 1, exportSchema = false)
abstract class CacheDatabase : RoomDatabase() {
    abstract fun getCacheDao(): CacheDao
}