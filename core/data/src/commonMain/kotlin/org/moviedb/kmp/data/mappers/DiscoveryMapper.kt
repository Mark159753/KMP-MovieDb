package org.moviedb.kmp.data.mappers

import org.moviedb.kmp.data.models.movie.MovieModel
import org.moviedb.kmp.database.entities.DiscoverEntity
import org.moviedb.kmp.network.models.discover.DiscoverItem

fun DiscoverItem.toEntity(
    pagingOrder: Double? = null
) = DiscoverEntity(
    adult = adult,
    backdropPath = backdropPath,
    genreIds = genreIds,
    id = id,
    originCountry = originCountry,
    originalLanguage = originalLanguage,
    originalName = originalName,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath,
    firstAirDate = firstAirDate,
    name = name,
    originalTitle = originalTitle,
    releaseDate = releaseDate,
    title = title,
    video = video,
    voteAverage = voteAverage,
    voteCount = voteCount,
    pagingOrder = pagingOrder
)

fun DiscoverEntity.toModel() = MovieModel(
    id = id,
    adult = adult,
    backdropPath = backdropPath,
    genreIds = genreIds ?: emptyList(),
    originalTitle = originalTitle ?: originalName ?: "",
    voteCount = voteCount ?: -1,
    video = video ?: false,
    title = title ?: name ?: "",
    releaseDate = releaseDate ?: firstAirDate ?: "",
    posterPath = posterPath,
    popularity = popularity ?: 0.0,
    originalLanguage = originalLanguage ?: "",
    overview = overview ?: "",
    voteAverage = voteAverage ?: 0.0
)