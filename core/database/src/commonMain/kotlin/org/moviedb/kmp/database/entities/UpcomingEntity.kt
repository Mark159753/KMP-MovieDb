package org.moviedb.kmp.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.moviedb.kmp.database.dao.UpcomingDao

@Entity(tableName = UpcomingDao.TABLE_NAME)
data class UpcomingEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val adult: Boolean,
    val backdropPath: String?,
    val genreIds: List<Int>,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String?,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int,
    val pagingOrder:Double? = null
)
