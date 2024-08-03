package org.moviedb.kmp.network.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.dsl.module
import org.moviedb.kmp.network.client.ApiService
import org.moviedb.kmp.network.utils.defaultConfig

actual fun provideHttpClientModule() = module {
    single<HttpClient> {
        HttpClient(Darwin){
            defaultConfig(get())

            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
                logger = object: Logger {
                    override fun log(message: String) {
                        co.touchlab.kermit.Logger.d(tag = "KtorClient", null){
                            message
                        }
                    }
                }
            }
        }
    }
}

class HttpClientModuleHelper():KoinComponent{

    private val apiService:ApiService by inject()

    fun apiService():ApiService = apiService
}