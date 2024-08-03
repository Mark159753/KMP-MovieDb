package org.moviedb.kmp.database.di

import androidx.room.RoomDatabase
import org.koin.dsl.module
import org.moviedb.kmp.database.MovieDb
import org.moviedb.kmp.database.getDatabaseBuilder

actual fun provideDatabaseModule() = module {
    single<RoomDatabase.Builder<MovieDb>> { getDatabaseBuilder(get()) }
}