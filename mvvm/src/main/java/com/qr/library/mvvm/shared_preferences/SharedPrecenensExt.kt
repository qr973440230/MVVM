package com.qr.library.mvvm.shared_preferences

import android.content.SharedPreferences
import com.tencent.mmkv.MMKV

fun sharedPreferences(): SharedPreferences = MMKV.defaultMMKV()

fun multiThreadSharedPreferences(): SharedPreferences =
    MMKV.mmkvWithID("MultiThreadSharedPreferences", MMKV.MULTI_PROCESS_MODE)
