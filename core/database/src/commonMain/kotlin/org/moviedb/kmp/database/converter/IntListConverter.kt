package org.moviedb.kmp.database.converter

import androidx.room.TypeConverter

class IntListConverter {

    @TypeConverter
    fun fromIntArrayList(value: List<Int>): String {
        return value.joinToString()
    }

    @TypeConverter
    fun toIntArrayList(value: String): List<Int> {
        return value.split(",")
            .mapNotNull { it.trim().toIntOrNull() }
    }
}