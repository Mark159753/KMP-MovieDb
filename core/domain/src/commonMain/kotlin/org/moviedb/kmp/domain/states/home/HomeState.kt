package org.moviedb.kmp.domain.states.home

import androidx.compose.runtime.Stable
import androidx.paging.cachedIn
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.moviedb.kmp.common.ApiResponse
import org.moviedb.kmp.data.repositories.movies.list.MoviesListRepository
import org.moviedb.kmp.data.repositories.trends.TrendsRepository
import org.moviedb.kmp.domain.actions.UIActionImp
import org.moviedb.kmp.domain.actions.UIActions
import org.moviedb.kmp.domain.usecase.GetTrendsUseCase

@Stable
class HomeState(
    private val trendsRepository: TrendsRepository,
    private val scope:CoroutineScope,
    getTrendsUseCase: GetTrendsUseCase,
    moviesListRepository: MoviesListRepository,
): UIActions by UIActionImp() {

    val trendsList = getTrendsUseCase()
        .stateIn(
            scope = scope,
            initialValue = emptyList(),
            started = SharingStarted.WhileSubscribed(5_000)
        )

    val upcomingMovies = moviesListRepository
        .getUpcomingPagingSource()
        .flowOn(Dispatchers.IO)
        .cachedIn(scope)


    val tvTrending = trendsRepository
        .getTvTrendsPagingSource()
        .flowOn(Dispatchers.IO)
        .cachedIn(scope)

    val trendingPeople = trendsRepository
        .getPeopleTrendsPagingSource()
        .flowOn(Dispatchers.IO)
        .cachedIn(scope)

    init {
        fetchAllTrends()
    }

    private fun fetchAllTrends(){
        scope.launch {
            val response = trendsRepository.fetchAllTrending()
            if (response is ApiResponse.Error){
                handleApiError(response)
            }
        }
    }

}