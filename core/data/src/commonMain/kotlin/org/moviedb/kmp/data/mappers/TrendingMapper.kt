package org.moviedb.kmp.data.mappers

import org.moviedb.kmp.common.constants.toMediaType
import org.moviedb.kmp.data.models.movie.MovieModel
import org.moviedb.kmp.data.models.trending.TrendingModel
import org.moviedb.kmp.database.entities.AllTrendingEntity
import org.moviedb.kmp.database.entities.TvTrendingEntity
import org.moviedb.kmp.network.models.trending.all.TrendingItem

fun TrendingItem.toEntity() = AllTrendingEntity(
    id = id,
    adult = adult,
    backdropPath = backdropPath,
    firstAirDate = firstAirDate,
    genreIds = genreIds,
    mediaType = mediaType ?: "",
    name = name,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    originalName = originalName,
    overview = overview,
    originCountry = originCountry,
    popularity = popularity,
    posterPath = posterPath,
    releaseDate = releaseDate,
    title = title,
    video = video ?: false,
    voteCount = voteCount,
    voteAverage = voteAverage
)

fun TrendingItem.toTvEntity(
    order:Double? = null
) = TvTrendingEntity(
    id = id,
    adult = adult,
    backdropPath = backdropPath,
    firstAirDate = firstAirDate,
    genreIds = genreIds,
    mediaType = mediaType ?: "",
    name = name,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    originalName = originalName,
    overview = overview,
    originCountry = originCountry,
    popularity = popularity,
    posterPath = posterPath,
    releaseDate = releaseDate,
    title = title,
    video = video ?: false,
    voteCount = voteCount,
    voteAverage = voteAverage,
    pagingOrder = order
)

fun AllTrendingEntity.toModel() = TrendingModel(
    id = id,
    adult = adult,
    backdropPath = backdropPath,
    firstAirDate = firstAirDate,
    genreIds = genreIds,
    mediaType = mediaType.toMediaType(),
    name = name,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    originalName = originalName,
    overview = overview,
    originCountry = originCountry,
    popularity = popularity,
    posterPath = posterPath,
    releaseDate = releaseDate,
    title = title,
    video = video,
    voteCount = voteCount,
    voteAverage = voteAverage
)

fun TvTrendingEntity.toModel() = TrendingModel(
    id = id,
    adult = adult,
    backdropPath = backdropPath,
    firstAirDate = firstAirDate,
    genreIds = genreIds,
    mediaType = mediaType.toMediaType(),
    name = name,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    originalName = originalName,
    overview = overview,
    originCountry = originCountry,
    popularity = popularity,
    posterPath = posterPath,
    releaseDate = releaseDate,
    title = title,
    video = video,
    voteCount = voteCount,
    voteAverage = voteAverage
)

fun TrendingModel.toMovieModel() = MovieModel(
    id = id,
    adult = adult,
    backdropPath = backdropPath,
    genreIds = genreIds,
    originalTitle = originalTitle ?: originalName ?: "",
    voteCount = voteCount,
    video = video,
    title = title ?: name ?: "",
    releaseDate = releaseDate ?: "",
    posterPath = posterPath,
    popularity = popularity,
    originalLanguage = originalLanguage,
    overview = overview,
    voteAverage = voteAverage
)