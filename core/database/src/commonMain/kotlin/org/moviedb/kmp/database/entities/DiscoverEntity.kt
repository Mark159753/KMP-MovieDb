package org.moviedb.kmp.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = DiscoverEntity.TABLE_NAME)
data class DiscoverEntity(
    val adult: Boolean,
    val backdropPath: String? = null,
    val genreIds: List<Int>? = null,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val originCountry: List<String>? = null,
    val originalLanguage: String? = null,
    val originalName: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val posterPath: String? = null,
    val firstAirDate: String? = null,
    val name: String? = null,
    val originalTitle: String? = null,
    val releaseDate: String? = null,
    val title: String? = null,
    val video: Boolean? = null,
    val voteAverage: Double? = null,
    val voteCount: Int? = null,
    val pagingOrder:Double? = null
){

    companion object{
        const val TABLE_NAME = "discovers_tab"
    }
}
