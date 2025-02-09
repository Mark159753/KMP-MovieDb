package org.moviedb.kmp.network.models.trending.all


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TrendingResponse(
    @SerialName("page")
    val page: Int,
    @SerialName("results")
    val results: List<TrendingItem>,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("total_results")
    val totalResults: Int
)