package com.qr.library.mvvm.db.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<T> {

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateAsync(vararg array: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateAsync(list: List<T>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(vararg array: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun update(list: List<T>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAsync(vararg array: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAsync(list: List<T>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg array: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(list: List<T>)

    @Delete
    suspend fun deleteAsync(vararg data: T)

    @Delete
    suspend fun deleteAsync(list: List<T>)

    @Delete
    fun delete(vararg data: T)

    @Delete
    fun delete(list: List<T>)
}