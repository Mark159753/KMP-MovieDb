package org.moviedb.kmp.data.models.trending

import org.moviedb.kmp.common.constants.MediaType

data class PersonModel(
    val id: Int,
    val adult: Boolean,
    val gender: Int,
    val knownFor: List<KnownForModel>,
    val knownForDepartment: String?,
    val mediaType: String,
    val name: String,
    val originalName: String,
    val popularity: Double,
    val profilePath: String?,
    val pagingOrder:Double? = null
)

data class KnownForModel(
    val id: Int,
    val adult: Boolean,
    val backdropPath: String?,
    val firstAirDate: String?,
    val genreIds: List<Int>,
    val mediaType: MediaType?,
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
