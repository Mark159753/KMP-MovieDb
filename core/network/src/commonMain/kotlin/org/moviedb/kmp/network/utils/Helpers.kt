package org.moviedb.kmp.network.utils

import MovieDB_KMP.core.network.BuildConfig
import co.touchlab.kermit.Logger
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.client.request.headers
import io.ktor.client.request.parameter
import io.ktor.client.request.request
import io.ktor.client.statement.HttpResponse
import io.ktor.http.parameters
import io.ktor.http.parametersOf
import io.ktor.serialization.JsonConvertException
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.network.UnresolvedAddressException
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import org.koin.core.parameter.parametersOf
import org.moviedb.kmp.common.ApiResponse
import org.moviedb.kmp.common.preferences.AppPreferences
import kotlin.math.ln

internal fun <T:HttpClientEngineConfig>HttpClientConfig<T>.defaultConfig(
    appPreferences: AppPreferences
){
    defaultRequest {
        url(BuildConfig.BASE_URL)
        headers {
            header("Authorization", "Bearer ${BuildConfig.API_TOKEN}")
        }
        runBlocking {
            appPreferences.currentLocale.firstOrNull()?.let { lng ->
                url.parameters.append("language", lng.name.lowercase())
            }
        }
    }
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        })
    }
}


suspend inline fun <reified T, reified E> HttpClient.safeRequest(
    block: HttpClient.() -> HttpResponse,
): ApiResponse<T, E> =
    try {
        val response = block()
        when {
            response.status.value in 400..500 -> ApiResponse.Error.HttpError(response.status.value, response.body<E>())
            else -> ApiResponse.Success(response.body())
        }
    } catch (e: ClientRequestException) {
        Logger.e("HttpClient", e)
        ApiResponse.Error.HttpError(e.response.status.value, e.errorBody())
    } catch (e: ServerResponseException) {
        Logger.e("HttpClient", e)
        ApiResponse.Error.HttpError(e.response.status.value, e.errorBody())
    } catch (e: IOException) {
        Logger.e("HttpClient", e)
        ApiResponse.Error.NetworkError
    } catch (e: UnresolvedAddressException){
        Logger.e("HttpClient", e)
        ApiResponse.Error.NetworkError
    } catch (e: SerializationException) {
        Logger.e("HttpClient", e)
        ApiResponse.Error.SerializationError
    } catch (e: JsonConvertException){
        Logger.e("HttpClient", e)
        ApiResponse.Error.SerializationError
    }

suspend inline fun <reified E> ResponseException.errorBody(): E? =
    try {
        response.body()
    } catch (e: SerializationException) {
        null
    }