package org.moviedb.kmp.database.converter

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.moviedb.kmp.database.entities.KnownFor

class KnowForConverter {

    @TypeConverter
    fun fromKnowForArrayList(value: List<KnownFor>): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toKnowForArrayList(value: String): List<KnownFor> {
        return Json.decodeFromString<List<KnownFor>>(value)
    }
}