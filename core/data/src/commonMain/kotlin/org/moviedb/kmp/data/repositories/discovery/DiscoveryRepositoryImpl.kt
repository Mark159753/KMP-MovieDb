package org.moviedb.kmp.data.repositories.discovery

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.moviedb.kmp.common.constants.ContentTypePath
import org.moviedb.kmp.common.constants.ExploreParam
import org.moviedb.kmp.data.models.movie.MovieModel
import org.moviedb.kmp.data.paging.discovery.DiscoveryLocalPagingSource
import org.moviedb.kmp.data.paging.discovery.DiscoveryPagingMediator
import org.moviedb.kmp.database.MovieDb
import org.moviedb.kmp.network.client.ApiService

class DiscoveryRepositoryImpl(
    private val db: MovieDb,
    private val apiService: ApiService,
) : DiscoveryRepository {


    override fun getDiscoveryPagingSource(getApiParam:()->Pair<ContentTypePath, List<ExploreParam>>): Flow<PagingData<MovieModel>> {
        val pagingSourceFactory = { DiscoveryLocalPagingSource(db) }

        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
            remoteMediator = DiscoveryPagingMediator(db, apiService, getApiParam),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }
}

private const val PAGE_SIZE = 20