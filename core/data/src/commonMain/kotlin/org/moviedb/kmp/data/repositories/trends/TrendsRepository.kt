package org.moviedb.kmp.data.repositories.trends

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.moviedb.kmp.common.constants.TimeWindow
import org.moviedb.kmp.data.models.movie.MovieModel
import org.moviedb.kmp.data.models.trending.PersonModel
import org.moviedb.kmp.data.models.trending.TrendingModel
import org.moviedb.kmp.network.client.GenericResponse

interface TrendsRepository{

    val allTrending:Flow<List<TrendingModel>>

    suspend fun fetchAllTrending(
        page:Int = 1,
        timeWindow: TimeWindow = TimeWindow.Day
    ):GenericResponse<List<TrendingModel>>

    fun getTvTrendsPagingSource():Flow<PagingData<MovieModel>>

    fun getPeopleTrendsPagingSource():Flow<PagingData<PersonModel>>
}