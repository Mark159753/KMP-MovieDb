package org.moviedb.kmp.data.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.moviedb.kmp.data.repositories.discovery.DiscoveryRepository
import org.moviedb.kmp.data.repositories.discovery.DiscoveryRepositoryImpl
import org.moviedb.kmp.data.repositories.genre.GenreRepository
import org.moviedb.kmp.data.repositories.genre.GenreRepositoryImpl
import org.moviedb.kmp.data.repositories.movies.list.MoviesListRepository
import org.moviedb.kmp.data.repositories.movies.list.MoviesListRepositoryImpl
import org.moviedb.kmp.data.repositories.trends.TrendsRepository
import org.moviedb.kmp.data.repositories.trends.TrendsRepositoryImpl

val repositoryModule = module {
    factoryOf(::MoviesListRepositoryImpl).bind<MoviesListRepository>()
    factoryOf(::TrendsRepositoryImpl).bind<TrendsRepository>()
    factoryOf(::GenreRepositoryImpl).bind<GenreRepository>()
    factoryOf(::DiscoveryRepositoryImpl).bind<DiscoveryRepository>()
}