package org.moviedb.kmp.network.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import org.koin.core.module.Module
import org.koin.dsl.module
import org.moviedb.kmp.network.client.ApiService
import org.moviedb.kmp.network.utils.defaultConfig

expect fun provideHttpClientModule():Module

val networkModule = module {
    includes(provideHttpClientModule())

    single<HttpClient> {
        HttpClient(CIO){
            defaultConfig(get())
        }
    }

    single<ApiService> { ApiService(get()) }
}