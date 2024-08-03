package org.moviedb.kmp.data.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import org.moviedb.kmp.data.paging.trending.people.PeoplePagingMediator
import org.moviedb.kmp.data.paging.trending.tv.TvTrendingPagingMediator
import org.moviedb.kmp.data.paging.upcoming.UpcomingMoviesPagingMediator

val pagingModule = module {
    factoryOf(::UpcomingMoviesPagingMediator)
    factoryOf(::TvTrendingPagingMediator)
    factoryOf(::PeoplePagingMediator)
}