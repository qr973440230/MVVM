package com.qr.library.mvvm.repository

import com.qr.library.mvvm.enums.Status

data class Resource<T>(
    val status: Status,
    val data: T? = null,
    val msg: String? = null
) {
    companion object {
        @JvmOverloads
        fun <T> loading(data: T? = null) = Resource(Status.LOADING, data)

        @JvmOverloads
        fun <T> success(data: T? = null) = Resource(Status.SUCCESS, data)

        @JvmOverloads
        fun <T> error(msg: String, data: T? = null) = Resource(Status.ERROR, data, msg)
    }
}