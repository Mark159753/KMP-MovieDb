package org.moviedb.kmp.network.client

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.moviedb.kmp.common.ApiResponse
import org.moviedb.kmp.common.constants.ContentTypePath
import org.moviedb.kmp.common.constants.ExploreParam
import org.moviedb.kmp.common.constants.TimeWindow
import org.moviedb.kmp.network.models.discover.DiscoverResponse
import org.moviedb.kmp.network.models.error.ErrorResponse
import org.moviedb.kmp.network.models.genres.GenresResponse
import org.moviedb.kmp.network.models.top_rated.TopRatedResponse
import org.moviedb.kmp.network.models.trending.all.TrendingResponse
import org.moviedb.kmp.network.models.trending.people.PeopleTrendingResponse
import org.moviedb.kmp.network.models.upcoming.UpcomingResponse
import org.moviedb.kmp.network.utils.safeRequest

class ApiService(
    private val client: HttpClient
) {

    suspend fun getTopRatedMovies(
        page:Int = 1
    ): GenericResponse<TopRatedResponse> {
        return client.safeRequest{
            get("/3/movie/top_rated"){
                parameter("page", page)
            }
        }
    }

    suspend fun getUpcoming(
        page:Int = 1,
    ): GenericResponse<UpcomingResponse> {
        return client.safeRequest{
            get("/3/movie/upcoming"){
                parameter("page", page)
            }
        }
    }


    /**
     * Trending
     * */

    suspend fun getAllTrending(
        page:Int = 1,
        timeWindow: TimeWindow = TimeWindow.Day
    ): GenericResponse<TrendingResponse> {
        return client.safeRequest{
            get("/3/trending/all/${timeWindow.name.lowercase()}"){
                parameter("page", page)
            }
        }
    }

    suspend fun getTvTrending(
        page:Int = 1,
        timeWindow: TimeWindow = TimeWindow.Day
    ): GenericResponse<TrendingResponse> {
        return client.safeRequest{
            get("/3/trending/tv/${timeWindow.name.lowercase()}"){
                parameter("page", page)
            }
        }
    }


    suspend fun getPeopleTrending(
        page:Int = 1,
        timeWindow: TimeWindow = TimeWindow.Day
    ): GenericResponse<PeopleTrendingResponse> {
        return client.safeRequest{
            get("3/trending/person/${timeWindow.name.lowercase()}"){
                parameter("page", page)
            }
        }
    }

    /**
     * Genres
     * */

    suspend fun getGenres(
        contentType: ContentTypePath = ContentTypePath.Movie
    ): GenericResponse<GenresResponse> {
        return client.safeRequest{
            get("3/genre/${contentType.name.lowercase()}/list")
        }
    }

    /**
     * Discovery
     * */

    suspend fun getDiscovery(
        page:Int = 1,
        contentType: ContentTypePath = ContentTypePath.Movie,
        params:List<ExploreParam> = emptyList()
    ): GenericResponse<DiscoverResponse> {
        return client.safeRequest{
            get("3/discover/${contentType.name.lowercase()}"){
                parameter("page", page)
                params.forEach { p ->
                    parameter(p.key, p.value)
                }
            }
        }
    }

}

typealias GenericResponse<S> = ApiResponse<S, ErrorResponse>