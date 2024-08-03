package org.moviedb.kmp.network.models.trending.people


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PeopleTrendingResponse(
    @SerialName("page")
    val page: Int,
    @SerialName("results")
    val results: List<PersonItem>,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("total_results")
    val totalResults: Int
)