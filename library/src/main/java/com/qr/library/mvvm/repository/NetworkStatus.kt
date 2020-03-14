package com.qr.library.mvvm.repository

import com.qr.library.mvvm.enums.Status

data class NetworkStatus(val status: Status, val msg: String? = null) {

    companion object {
        val LOADING =
            NetworkStatus(Status.LOADING)
        val SUCCESS =
            NetworkStatus(Status.SUCCESS)

        @JvmOverloads
        fun error(msg: String? = null) = NetworkStatus(Status.ERROR, msg)
    }
}