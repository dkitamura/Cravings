package com.dkitamura.crave.database

import androidx.room.TypeConverter
import java.util.*


object StringListConverter {
    @TypeConverter
    fun storedStringToList(value: String): List<String> {
        val vals: List<String> = value.split("\\s*,\\s*".toRegex())
        return vals
    }

    @TypeConverter
    fun languagesToStoredString(input: List<String>): String {
        var value = ""
        for (i in input) {
            value += "$i,"
        }
        value = value.dropLast(1)
        return value
    }
}