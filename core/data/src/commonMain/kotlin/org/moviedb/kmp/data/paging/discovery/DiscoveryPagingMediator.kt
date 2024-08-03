package org.moviedb.kmp.data.paging.discovery

import org.moviedb.kmp.common.ApiResponse
import org.moviedb.kmp.common.constants.ContentTypePath
import org.moviedb.kmp.common.constants.ExploreParam
import org.moviedb.kmp.data.mappers.toEntity
import org.moviedb.kmp.data.models.movie.MovieModel
import org.moviedb.kmp.data.paging.base.BasePagingMediator
import org.moviedb.kmp.database.MovieDb
import org.moviedb.kmp.database.entities.DiscoverEntity
import org.moviedb.kmp.database.entities.REMOTE_KEY_DISCOVERY
import org.moviedb.kmp.database.entities.RemoteKeyEntity
import org.moviedb.kmp.network.client.ApiService
import org.moviedb.kmp.network.client.GenericResponse
import org.moviedb.kmp.network.models.discover.DiscoverItem

class DiscoveryPagingMediator(
    private val db: MovieDb,
    private val apiService: ApiService,
    private val getApiParam:()->Pair<ContentTypePath, List<ExploreParam>>
): BasePagingMediator<MovieModel, DiscoverEntity, DiscoverItem>(db.getDiscoverDao(), db.getRemoteKeyDao(), REMOTE_KEY_DISCOVERY) {

    override suspend fun apiCall(page: Int): GenericResponse<List<DiscoverItem>> {
        val params = getApiParam()
        return when(val response = apiService.getDiscovery(page = page, contentType = params.first, params = params.second)){
            is ApiResponse.Error -> response
            is ApiResponse.Success -> ApiResponse.Success(response.body.results)
        }
    }

    override fun toRemoteKey(
        prevKey: Int?,
        nextKey: Int?,
        item: DiscoverItem
    ) = RemoteKeyEntity("${item.id}_$REMOTE_KEY_DISCOVERY", prevKey, nextKey, REMOTE_KEY_DISCOVERY)

    override fun toEntity(item: DiscoverItem, order: Double?) = item.toEntity(order)

    override suspend fun findRemoteKey(
        item: MovieModel?
    ) = item?.id?.let { db.getRemoteKeyDao().getItemById("${item.id}_$REMOTE_KEY_DISCOVERY") }
}