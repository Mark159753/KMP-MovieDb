package org.moviedb.kmp.database.di

import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.moviedb.kmp.database.preferences.AppPreferencesImpl

expect fun provideDataStoreModule(): Module

val dataStoreModule = module {
    includes(provideDataStoreModule())

    singleOf(::AppPreferencesImpl).bind<org.moviedb.kmp.common.preferences.AppPreferences>()
}