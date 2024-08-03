package org.moviedb.kmp.domain.di

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.moviedb.kmp.domain.states.explore.filter.ExploreFilterState
import org.moviedb.kmp.domain.states.home.HomeState

class UseCaseHelper:KoinComponent {

    private val homeState:HomeState by inject()
    private val exploreFilterState: ExploreFilterState by inject()

    fun homeState() = homeState

    fun exploreFilterState() = exploreFilterState
}