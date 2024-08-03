package org.moviedb.kmp.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.moviedb.kmp.common.constants.ContentTypePath

@Entity(tableName = "genres_tab")
data class GenreEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val typePath: ContentTypePath
)
