package org.moviedb.kmp.data.paging.trending.tv

import org.moviedb.kmp.common.ApiResponse
import org.moviedb.kmp.data.mappers.toTvEntity
import org.moviedb.kmp.data.models.trending.TrendingModel
import org.moviedb.kmp.data.paging.base.BasePagingMediator
import org.moviedb.kmp.database.dao.RemoteKeyDao
import org.moviedb.kmp.database.dao.TvTrendingDao
import org.moviedb.kmp.database.entities.REMOTE_KEY_TV_TRENDING
import org.moviedb.kmp.database.entities.RemoteKeyEntity
import org.moviedb.kmp.database.entities.TvTrendingEntity
import org.moviedb.kmp.network.client.ApiService
import org.moviedb.kmp.network.client.GenericResponse
import org.moviedb.kmp.network.models.trending.all.TrendingItem

class TvTrendingPagingMediator(
    private val apiService: ApiService,
    upcomingDao: TvTrendingDao,
    private val remoteKeysDao: RemoteKeyDao,
): BasePagingMediator<TrendingModel, TvTrendingEntity, TrendingItem>(upcomingDao, remoteKeysDao, REMOTE_KEY_TV_TRENDING) {

    override suspend fun apiCall(page: Int): GenericResponse<List<TrendingItem>> {
        return when(val response = apiService.getTvTrending(page = page)){
            is ApiResponse.Error -> response
            is ApiResponse.Success -> ApiResponse.Success(response.body.results)
        }
    }

    override fun toRemoteKey(
        prevKey: Int?,
        nextKey: Int?,
        item: TrendingItem
    )  = RemoteKeyEntity("${item.id}_$REMOTE_KEY_TV_TRENDING", prevKey, nextKey, REMOTE_KEY_TV_TRENDING)

    override fun toEntity(item: TrendingItem, order: Double?) = item.toTvEntity(order)

    override suspend fun findRemoteKey(item: TrendingModel?) = item?.id?.let { remoteKeysDao.getItemById("${item.id}_$REMOTE_KEY_TV_TRENDING") }
}