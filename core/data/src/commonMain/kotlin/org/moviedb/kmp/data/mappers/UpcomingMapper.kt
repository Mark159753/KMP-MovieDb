package org.moviedb.kmp.data.mappers

import org.moviedb.kmp.data.models.movie.MovieModel
import org.moviedb.kmp.database.entities.UpcomingEntity
import org.moviedb.kmp.network.models.upcoming.UpcomingItem

fun UpcomingItem.toEntity(
    pagingOrder:Double? = null
) = UpcomingEntity(
    id = id,
    adult = adult,
    backdropPath = backdropPath,
    genreIds = genreIds,
    originalTitle = originalTitle,
    voteCount = voteCount,
    video = video,
    title = title,
    releaseDate = releaseDate,
    posterPath = posterPath,
    popularity = popularity,
    originalLanguage = originalLanguage,
    overview = overview,
    voteAverage = voteAverage,
    pagingOrder = pagingOrder,
)

fun UpcomingEntity.toModel() = MovieModel(
    id = id,
    adult = adult,
    backdropPath = backdropPath,
    genreIds = genreIds,
    originalTitle = originalTitle,
    voteCount = voteCount,
    video = video,
    title = title,
    releaseDate = releaseDate,
    posterPath = posterPath,
    popularity = popularity,
    originalLanguage = originalLanguage,
    overview = overview,
    voteAverage = voteAverage
)