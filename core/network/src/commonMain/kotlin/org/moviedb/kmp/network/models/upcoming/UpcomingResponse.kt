package org.moviedb.kmp.network.models.upcoming


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpcomingResponse(
    @SerialName("page")
    val page: Int,
    @SerialName("results")
    val results: List<UpcomingItem>,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("total_results")
    val totalResults: Int
)