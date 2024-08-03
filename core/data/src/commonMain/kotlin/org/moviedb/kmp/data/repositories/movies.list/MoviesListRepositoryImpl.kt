package org.moviedb.kmp.data.repositories.movies.list

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.moviedb.kmp.common.ApiResponse
import org.moviedb.kmp.data.mappers.toModel
import org.moviedb.kmp.data.mappers.toTopRatedEntity
import org.moviedb.kmp.data.models.movie.MovieModel
import org.moviedb.kmp.data.paging.upcoming.UpcomingLocalPagingSource
import org.moviedb.kmp.data.paging.upcoming.UpcomingMoviesPagingMediator
import org.moviedb.kmp.database.MovieDb
import org.moviedb.kmp.database.dao.TopRatedMovieDao
import org.moviedb.kmp.database.dao.UpcomingDao
import org.moviedb.kmp.network.client.ApiService

class MoviesListRepositoryImpl(
    private val apiService: ApiService,
    private val topRatedMoviesDao: TopRatedMovieDao,
    private val upcomingMoviesPagingMediator: UpcomingMoviesPagingMediator,
    private val upcomingDao: UpcomingDao,
    private val db: MovieDb,
): MoviesListRepository {

    override val topRatedMovies: Flow<List<MovieModel>>
        get() = topRatedMoviesDao.getAllFlow().map { list -> list.map { it.toModel() } }

    override suspend fun fetchTopRatedMovies(page: Int): List<MovieModel> {
        return when(val response = apiService.getTopRatedMovies(page = page)){
            is ApiResponse.Error.HttpError,
            ApiResponse.Error.NetworkError,
            ApiResponse.Error.SerializationError -> emptyList()

            is ApiResponse.Success -> {
                val entities = response.body.results?.map { it.toTopRatedEntity() } ?: emptyList()
                topRatedMoviesDao.deleteAllItems()
                topRatedMoviesDao.insertItems(entities)
                entities.map { it.toModel() }
            }
        }
    }

    override fun getUpcomingPagingSource(): Flow<PagingData<MovieModel>> {
        val pagingSourceFactory = { UpcomingLocalPagingSource(db, upcomingDao) }

        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
            remoteMediator = upcomingMoviesPagingMediator,
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }
}

private const val PAGE_SIZE = 20