package com.qr.library.mvvm.livedata

import androidx.lifecycle.LiveData
import io.reactivex.rxjava3.core.Maybe

class AbsentLiveData<T : Any?> private constructor() : LiveData<T>() {
    init {
        postValue(null)
    }

    companion object {
        fun <T> create(): LiveData<T> {
            return AbsentLiveData()
        }
    }
}
