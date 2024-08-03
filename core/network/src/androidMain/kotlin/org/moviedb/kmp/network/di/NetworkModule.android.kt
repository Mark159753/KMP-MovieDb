package org.moviedb.kmp.network.di

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import org.moviedb.kmp.network.utils.defaultConfig


actual fun provideHttpClientModule() = module {
    single<HttpClient> {
        HttpClient(OkHttp) {
            defaultConfig(get())
            engine {
                config {
                    followRedirects(true)
                }
                val logger =  HttpLoggingInterceptor()
                logger.level = HttpLoggingInterceptor.Level.BODY
                addInterceptor(logger)
            }
        }
    }
}