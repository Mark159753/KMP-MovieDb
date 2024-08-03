package org.moviedb.kmp.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = TvTrendingEntity.TABLE_NAME)
data class TvTrendingEntity(
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
    val voteCount: Int,
    val pagingOrder:Double? = null
){

    companion object{
        const val TABLE_NAME = "tv_trending_table"
    }
}