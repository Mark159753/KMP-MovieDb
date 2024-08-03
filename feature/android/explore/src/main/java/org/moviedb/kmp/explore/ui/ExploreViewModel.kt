package org.moviedb.kmp.explore.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import org.moviedb.kmp.data.repositories.discovery.DiscoveryRepository
import org.moviedb.kmp.data.repositories.genre.GenreRepository
import org.moviedb.kmp.domain.states.explore.filter.ExploreFilterState
import org.moviedb.kmp.domain.states.explore.filter.ExploreFilterStateImpl

class ExploreViewModel(
    genreRepository: GenreRepository,
    discoveryRepository: DiscoveryRepository
): ViewModel() {

    val state: ExploreFilterState = ExploreFilterStateImpl(
        repository = genreRepository,
        scope = viewModelScope,
        discoveryRepository = discoveryRepository
    )
}