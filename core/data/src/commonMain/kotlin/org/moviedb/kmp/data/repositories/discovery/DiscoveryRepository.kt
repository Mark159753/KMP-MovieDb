package org.moviedb.kmp.data.repositories.discovery

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.moviedb.kmp.common.constants.ContentTypePath
import org.moviedb.kmp.common.constants.ExploreParam
import org.moviedb.kmp.data.models.movie.MovieModel

interface DiscoveryRepository {

    fun getDiscoveryPagingSource(getApiParam:()->Pair<ContentTypePath, List<ExploreParam>>): Flow<PagingData<MovieModel>>
}