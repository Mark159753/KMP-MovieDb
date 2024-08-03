package org.moviedb.kmp.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import org.moviedb.kmp.data.repositories.movies.list.MoviesListRepository
import org.moviedb.kmp.data.repositories.trends.TrendsRepository
import org.moviedb.kmp.domain.states.home.HomeState
import org.moviedb.kmp.domain.usecase.GetTrendsUseCase

class HomeViewModel(
    trendsRepository: TrendsRepository,
    moviesListRepository: MoviesListRepository,
    getTrendsUseCase: GetTrendsUseCase,
):ViewModel() {

    val state = HomeState(
        trendsRepository = trendsRepository,
        scope = viewModelScope,
        getTrendsUseCase = getTrendsUseCase,
        moviesListRepository = moviesListRepository,
    )

}