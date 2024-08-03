package org.moviedb.kmp.database.di.helpers

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.moviedb.kmp.common.preferences.AppPreferences
import org.moviedb.kmp.database.MovieDb
import org.moviedb.kmp.database.dao.TopRatedMovieDao

class DatabaseModuleHelper: KoinComponent {

    private val db: MovieDb by inject()
    private val topRatedMovieDao: TopRatedMovieDao by inject()
    private val appPreferences:AppPreferences by inject()

    fun db() = db

    fun topRatedMovieDao() = topRatedMovieDao

    fun appPreferences() = appPreferences

}