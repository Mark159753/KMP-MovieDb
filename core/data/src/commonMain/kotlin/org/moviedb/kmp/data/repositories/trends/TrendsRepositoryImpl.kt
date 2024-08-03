package org.moviedb.kmp.data.repositories.trends

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.moviedb.kmp.common.ApiResponse
import org.moviedb.kmp.common.constants.TimeWindow
import org.moviedb.kmp.data.mappers.toEntity
import org.moviedb.kmp.data.mappers.toModel
import org.moviedb.kmp.data.mappers.toMovieModel
import org.moviedb.kmp.data.models.movie.MovieModel
import org.moviedb.kmp.data.models.trending.PersonModel
import org.moviedb.kmp.data.models.trending.TrendingModel
import org.moviedb.kmp.data.paging.trending.people.PeopleLocalPagingSource
import org.moviedb.kmp.data.paging.trending.people.PeoplePagingMediator
import org.moviedb.kmp.data.paging.trending.tv.TvTrendingLocalPagingSource
import org.moviedb.kmp.data.paging.trending.tv.TvTrendingPagingMediator
import org.moviedb.kmp.database.MovieDb
import org.moviedb.kmp.database.dao.AllTrendingDao
import org.moviedb.kmp.network.client.ApiService
import org.moviedb.kmp.network.client.GenericResponse

class TrendsRepositoryImpl(
    private val apiService: ApiService,
    private val allTrendingDao: AllTrendingDao,
    private val db: MovieDb,
    private val tvTrendingPagingMediator: TvTrendingPagingMediator,
    private val peopleTrendingPagingMediator: PeoplePagingMediator,
) : TrendsRepository {

    override val allTrending: Flow<List<TrendingModel>>
        get() = allTrendingDao.getAllFlow().map { list ->
            list.map { it.toModel() }
        }

    override suspend fun fetchAllTrending(
        page: Int,
        timeWindow: TimeWindow
    ): GenericResponse<List<TrendingModel>> {
        return when (val response =
            apiService.getAllTrending(page = page, timeWindow = timeWindow)) {
            is ApiResponse.Error -> response
            is ApiResponse.Success -> {
                val entities = response.body.results.map { it.toEntity() }
                allTrendingDao.deleteAllItems()
                allTrendingDao.insertItems(entities)
                ApiResponse.Success(entities.map { it.toModel() })
            }
        }
    }

    override fun getTvTrendsPagingSource(): Flow<PagingData<MovieModel>> {
        val pagingSourceFactory = { TvTrendingLocalPagingSource(db, db.getTvTrendingDao()) }

        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
            remoteMediator = tvTrendingPagingMediator,
            pagingSourceFactory = pagingSourceFactory
        ).flow
            .map { paging ->
                paging.map { it.toMovieModel() }
            }
    }

    override fun getPeopleTrendsPagingSource(): Flow<PagingData<PersonModel>> {
        val pagingSourceFactory = { PeopleLocalPagingSource(db) }

        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
            remoteMediator = peopleTrendingPagingMediator,
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }
}

private const val PAGE_SIZE = 20