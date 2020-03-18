
package com.qr.library.mvvm.gson

import com.google.gson.Gson

inline fun <reified T> Gson.fromJson(json: String): T = fromJson(json, T::class.java)

fun <T> T.toJson(gson: Gson = Gson()): String = gson.toJson(this)


