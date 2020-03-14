package com.qr.library.mvvm.db.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<T> {

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(vararg array: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(list: List<T>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg array: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<T>)

    @Delete
    suspend fun delete(vararg data: T)

    @Delete
    suspend fun delete(list: List<T>)
}