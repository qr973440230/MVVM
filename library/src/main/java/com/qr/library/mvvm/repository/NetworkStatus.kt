package com.qr.library.mvvm.repository

import com.qr.library.mvvm.enums.Status

data class NetworkStatus(val status: Status, val msg: String? = null) {

    companion object {
        val LOADING =
            NetworkStatus(Status.LOADING)
        val SUCCESS =
            NetworkStatus(Status.SUCCESS)
        fun error(msg: String?) =
            NetworkStatus(Status.ERROR, msg)
    }
}