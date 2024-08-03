package org.moviedb.kmp.domain.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.moviedb.kmp.domain.states.explore.filter.ExploreFilterState
import org.moviedb.kmp.domain.states.explore.filter.ExploreFilterStateImpl


actual fun singleStateModule() = module {
    singleOf(::ExploreFilterStateImpl).bind<ExploreFilterState>()
}