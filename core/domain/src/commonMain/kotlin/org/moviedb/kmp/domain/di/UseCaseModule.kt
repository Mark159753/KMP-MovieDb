package org.moviedb.kmp.domain.di

import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.moviedb.kmp.domain.actions.UIActionImp
import org.moviedb.kmp.domain.actions.UIActions
import org.moviedb.kmp.domain.states.explore.filter.ContentTypeState
import org.moviedb.kmp.domain.states.explore.filter.ContentTypeStateImpl
import org.moviedb.kmp.domain.states.explore.filter.ExploreFilterState
import org.moviedb.kmp.domain.states.explore.filter.ExploreFilterStateImpl
import org.moviedb.kmp.domain.states.explore.filter.FilterGenreState
import org.moviedb.kmp.domain.states.explore.filter.FilterGenreStateImpl
import org.moviedb.kmp.domain.states.explore.filter.ReleaseYearStateFilter
import org.moviedb.kmp.domain.states.explore.filter.ReleaseYearStateFilterImpl
import org.moviedb.kmp.domain.states.explore.filter.SortState
import org.moviedb.kmp.domain.states.explore.filter.SortStateImpl
import org.moviedb.kmp.domain.states.home.HomeState
import org.moviedb.kmp.domain.usecase.GetTrendsUseCase

expect fun singleStateModule():Module

val useCaseModule = module {
    factoryOf(::GetTrendsUseCase)

    factoryOf(::HomeState)

    factoryOf(::UIActionImp).bind<UIActions>()
    factoryOf(::SortStateImpl).bind<SortState>()
    factoryOf(::ContentTypeStateImpl).bind<ContentTypeState>()
    factoryOf(::ReleaseYearStateFilterImpl).bind<ReleaseYearStateFilter>()
    factoryOf(::FilterGenreStateImpl).bind<FilterGenreState>()

    includes(singleStateModule())
}