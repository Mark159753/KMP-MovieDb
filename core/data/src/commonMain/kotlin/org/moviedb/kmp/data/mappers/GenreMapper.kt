package org.moviedb.kmp.data.mappers

import org.moviedb.kmp.common.constants.ContentTypePath
import org.moviedb.kmp.data.models.genres.GenreModel
import org.moviedb.kmp.database.entities.GenreEntity
import org.moviedb.kmp.network.models.genres.Genre

fun Genre.toEntity(type:ContentTypePath) = GenreEntity(
    id = id,
    name = name,
    typePath = type
)

fun GenreEntity.toModel() = GenreModel(
    id = id,
    name = name,
)