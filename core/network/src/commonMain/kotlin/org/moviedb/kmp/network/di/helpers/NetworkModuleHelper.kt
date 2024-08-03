package org.moviedb.kmp.network.di.helpers

import io.ktor.client.HttpClient
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.moviedb.kmp.network.client.ApiService

class NetworkModuleHelper: KoinComponent {

    private val httpClient: HttpClient by inject()
    private val apiService: ApiService by inject()

    fun httpClient() = httpClient

    fun apiService() = apiService

}