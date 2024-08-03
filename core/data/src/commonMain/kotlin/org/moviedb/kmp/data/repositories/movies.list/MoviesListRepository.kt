package org.moviedb.kmp.data.repositories.movies.list

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.moviedb.kmp.data.models.movie.MovieModel

interface MoviesListRepository {

    val topRatedMovies:Flow<List<MovieModel>>

    suspend fun fetchTopRatedMovies(
        page:Int
    ):List<MovieModel>


    fun getUpcomingPagingSource():Flow<PagingData<MovieModel>>


}