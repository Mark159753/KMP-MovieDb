package org.moviedb.kmp.data.paging.upcoming

import org.moviedb.kmp.common.ApiResponse
import org.moviedb.kmp.data.mappers.toEntity
import org.moviedb.kmp.data.models.movie.MovieModel
import org.moviedb.kmp.data.paging.base.BasePagingMediator
import org.moviedb.kmp.database.dao.RemoteKeyDao
import org.moviedb.kmp.database.dao.UpcomingDao
import org.moviedb.kmp.database.entities.REMOTE_KEY_UPCOMING
import org.moviedb.kmp.database.entities.RemoteKeyEntity
import org.moviedb.kmp.database.entities.UpcomingEntity
import org.moviedb.kmp.network.client.ApiService
import org.moviedb.kmp.network.client.GenericResponse
import org.moviedb.kmp.network.models.upcoming.UpcomingItem

class UpcomingMoviesPagingMediator(
    private val apiService: ApiService,
    upcomingDao: UpcomingDao,
    private val remoteKeysDao: RemoteKeyDao,
):BasePagingMediator<MovieModel, UpcomingEntity, UpcomingItem>(upcomingDao, remoteKeysDao, REMOTE_KEY_UPCOMING) {

    override suspend fun apiCall(page: Int): GenericResponse<List<UpcomingItem>> {
        return when(val response = apiService.getUpcoming(page = page)){
            is ApiResponse.Error -> response
            is ApiResponse.Success -> ApiResponse.Success(response.body.results)
        }
    }

    override fun toRemoteKey(
        prevKey: Int?,
        nextKey: Int?,
        item: UpcomingItem
    ) = RemoteKeyEntity("${item.id}_$REMOTE_KEY_UPCOMING", prevKey, nextKey, REMOTE_KEY_UPCOMING)

    override fun toEntity(item: UpcomingItem, order: Double?) = item.toEntity(order)

    override suspend fun findRemoteKey(item: MovieModel?) = item?.id?.let { remoteKeysDao.getItemById("${item.id}_$REMOTE_KEY_UPCOMING") }
}