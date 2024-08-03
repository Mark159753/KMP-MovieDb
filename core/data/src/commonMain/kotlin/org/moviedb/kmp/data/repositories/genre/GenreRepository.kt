package org.moviedb.kmp.data.repositories.genre

import kotlinx.coroutines.flow.Flow
import org.moviedb.kmp.common.constants.ContentTypePath
import org.moviedb.kmp.data.models.genres.GenreModel
import org.moviedb.kmp.network.client.GenericResponse

interface GenreRepository {

    val movieGenres:Flow<List<GenreModel>>
    val tvGenres:Flow<List<GenreModel>>

    suspend fun fetchGenres(type: ContentTypePath):GenericResponse<List<GenreModel>>
}