package org.moviedb.kmp.network.models.top_rated


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TopRatedResponse(
    @SerialName("page")
    val page: Int?,
    @SerialName("results")
    val results: List<MovieItem>?,
    @SerialName("total_pages")
    val totalPages: Int?,
    @SerialName("total_results")
    val totalResults: Int?
)