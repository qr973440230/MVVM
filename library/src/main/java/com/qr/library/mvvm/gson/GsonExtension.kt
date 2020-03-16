@file:Suppress("NOTHING_TO_INLINE")

package com.qr.library.mvvm.gson

import com.google.gson.Gson

inline fun <reified T> Gson.fromJson(json: String): T = fromJson(json, T::class.java)

inline fun <T> T.toJson(gson: Gson = Gson()): String = gson.toJson(this)


