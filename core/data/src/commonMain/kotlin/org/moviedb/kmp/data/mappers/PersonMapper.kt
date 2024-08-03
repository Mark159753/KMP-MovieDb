package org.moviedb.kmp.data.mappers

import org.moviedb.kmp.common.constants.toMediaType
import org.moviedb.kmp.data.models.trending.KnownForModel
import org.moviedb.kmp.data.models.trending.PersonModel
import org.moviedb.kmp.database.entities.KnownFor
import org.moviedb.kmp.database.entities.PersonEntity
import org.moviedb.kmp.network.models.trending.all.TrendingItem
import org.moviedb.kmp.network.models.trending.people.PersonItem

fun PersonItem.toEntity(
    pagingOrder:Double? = null,
) = PersonEntity(
    id = id,
    adult = adult,
    gender = gender,
    knownFor = knownFor.map { it.toKnowFor() },
    knownForDepartment = knownForDepartment,
    mediaType = mediaType,
    originalName = originalName,
    name = name,
    popularity = popularity,
    profilePath = profilePath,
    pagingOrder = pagingOrder
)

fun TrendingItem.toKnowFor() = KnownFor(
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

fun PersonEntity.toModel() = PersonModel(
    id = id,
    adult = adult,
    gender = gender,
    knownFor = knownFor.map { it.toModel() },
    knownForDepartment = knownForDepartment,
    mediaType = mediaType,
    originalName = originalName,
    name = name,
    popularity = popularity,
    profilePath = profilePath,
    pagingOrder = pagingOrder
)

fun KnownFor.toModel() = KnownForModel(
    id = id,
    adult = adult,
    backdropPath = backdropPath,
    firstAirDate = firstAirDate,
    genreIds = genreIds,
    mediaType = mediaType?.toMediaType(),
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