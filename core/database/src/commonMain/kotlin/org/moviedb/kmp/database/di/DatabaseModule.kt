package org.moviedb.kmp.database.di

import org.koin.core.module.Module
import org.koin.dsl.module
import org.moviedb.kmp.database.MovieDb
import org.moviedb.kmp.database.dao.AllTrendingDao
import org.moviedb.kmp.database.dao.PersonDao
import org.moviedb.kmp.database.dao.RemoteKeyDao
import org.moviedb.kmp.database.dao.TopRatedMovieDao
import org.moviedb.kmp.database.dao.TvTrendingDao
import org.moviedb.kmp.database.dao.UpcomingDao

expect fun provideDatabaseModule():Module

val databaseModule = module {
    includes(provideDatabaseModule())

    single<MovieDb> { MovieDb.createDataBase(get()) }

    factory<TopRatedMovieDao> {
        val movieDb: MovieDb = get()
        movieDb.getTopRatedMovieDaoDao()
    }

    factory<AllTrendingDao> {
        val movieDb: MovieDb = get()
        movieDb.getAllTrendingDao()
    }


    factory<UpcomingDao> {
        val movieDb: MovieDb = get()
        movieDb.getUpcomingDao()
    }

    factory<RemoteKeyDao> {
        val movieDb: MovieDb = get()
        movieDb.getRemoteKeyDao()
    }
    factory<TvTrendingDao> {
        val movieDb: MovieDb = get()
        movieDb.getTvTrendingDao()
    }
    factory<PersonDao> {
        val movieDb: MovieDb = get()
        movieDb.getPersonDao()
    }
}