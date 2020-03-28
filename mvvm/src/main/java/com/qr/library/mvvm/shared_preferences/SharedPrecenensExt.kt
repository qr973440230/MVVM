package com.qr.library.mvvm.shared_preferences

import android.content.SharedPreferences
import com.tencent.mmkv.MMKV

val sharedPreferences: SharedPreferences by lazy {
    MMKV.defaultMMKV()
}

val multiProcessSharedPreferences: SharedPreferences by lazy {
    MMKV.mmkvWithID("MultiProcessSharedPreferences", MMKV.MULTI_PROCESS_MODE)
}
