package com.qr.library.mvvm.cache

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Cache(
    @PrimaryKey
    val key: String,
    val data: String
)
