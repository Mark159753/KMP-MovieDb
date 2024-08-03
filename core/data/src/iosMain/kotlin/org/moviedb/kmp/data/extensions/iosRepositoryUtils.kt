package org.moviedb.kmp.data.extensions

import org.moviedb.kmp.common.wrapper.CFlow
import org.moviedb.kmp.common.wrapper.wrap
import org.moviedb.kmp.data.models.movie.MovieModel
import org.moviedb.kmp.data.repositories.movies.list.MoviesListRepository

fun MoviesListRepository.watchTopRatedMovies():CFlow<List<MovieModel>> = topRatedMovies.wrap()