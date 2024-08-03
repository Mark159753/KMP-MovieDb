package org.moviedb.kmp.data.repositories.genre

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.moviedb.kmp.common.ApiResponse
import org.moviedb.kmp.common.constants.ContentTypePath
import org.moviedb.kmp.data.mappers.toEntity
import org.moviedb.kmp.data.mappers.toModel
import org.moviedb.kmp.data.models.genres.GenreModel
import org.moviedb.kmp.database.MovieDb
import org.moviedb.kmp.network.client.ApiService
import org.moviedb.kmp.network.client.GenericResponse

class GenreRepositoryImpl(
    private val apiService:ApiService,
    private val db:MovieDb
) : GenreRepository {

    override val movieGenres: Flow<List<GenreModel>>
        get() = db.getGenresDao().getAllByType(ContentTypePath.Movie).map { list ->
            list.map { it.toModel() }
        }

    override val tvGenres: Flow<List<GenreModel>>
        get() = db.getGenresDao().getAllByType(ContentTypePath.Tv).map { list ->
            list.map { it.toModel() }
        }

    override suspend fun fetchGenres(type: ContentTypePath): GenericResponse<List<GenreModel>> {
        return when (val response =
            apiService.getGenres(contentType = type)) {
            is ApiResponse.Error -> response
            is ApiResponse.Success -> {
                val entities = response.body.genres.map { it.toEntity(type) }
                val dao = db.getGenresDao()
                dao.deleteAllByType(type)
                dao.insertItems(entities)
                ApiResponse.Success(entities.map { it.toModel() })
            }
        }
    }
}