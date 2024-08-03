package org.moviedb.kmp.data.mappers

import org.moviedb.kmp.data.models.movie.MovieModel
import org.moviedb.kmp.database.entities.TopRatedMovieEntity
import org.moviedb.kmp.network.models.top_rated.MovieItem

fun MovieItem.toTopRatedEntity() = TopRatedMovieEntity(
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

fun TopRatedMovieEntity.toModel() = MovieModel(
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