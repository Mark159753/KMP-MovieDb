package org.moviedb.kmp.data.di.helpers

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.moviedb.kmp.data.repositories.movies.list.MoviesListRepository

class RepositoryModuleHelper: KoinComponent {

    private val moviesListRepository: MoviesListRepository by inject()

    fun moviesListRepository(): MoviesListRepository = moviesListRepository
}