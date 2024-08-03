package org.moviedb.kmp.database.converter

import androidx.room.TypeConverter

class StringListConverter {

    @TypeConverter
    fun fromStringArrayList(value: List<String>): String {
        return value.joinToString()
    }

    @TypeConverter
    fun toStringArrayList(value: String): List<String> {
        return value.split(",")
    }
}