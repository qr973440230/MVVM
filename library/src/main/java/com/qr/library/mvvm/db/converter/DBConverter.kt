package com.qr.library.mvvm.db.converter

import androidx.room.TypeConverter
import java.lang.NumberFormatException
import java.util.*

object DBConverter {
    @TypeConverter
    @JvmStatic
    fun fromTimeStamp(timeStamp: Long?): Date? {
        return if (timeStamp == null) null else Date(timeStamp)
    }

    @TypeConverter
    @JvmStatic
    fun toTimeStamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    @JvmStatic
    fun stringToIntList(data: String?): List<Int>? {
        return data?.split(",")?.mapNotNull { str ->
            try {
                str.toInt()
            } catch (ex: NumberFormatException) {
                null
            }
        }
    }

    @TypeConverter
    @JvmStatic
    fun intListToString(ints: List<Int>?): String? {
        return ints?.joinToString(separator = ",")
    }


}