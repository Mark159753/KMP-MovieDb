package org.moviedb.kmp.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "all_trending_table")
data class AllTrendingEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val adult: Boolean,
    val backdropPath: String?,
    val firstAirDate: String?,
    val genreIds: List<Int>,
    val mediaType: String,
    val name: String?,
    val originCountry: List<String>?,
    val originalLanguage: String,
    val originalName: String?,
    val originalTitle: String?,
    val overview: String,
    val popularity: Double,
    val posterPath: String?,
    val releaseDate: String?,
    val title: String?,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
)
