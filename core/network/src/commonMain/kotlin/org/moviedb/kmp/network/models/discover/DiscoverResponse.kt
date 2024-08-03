package org.moviedb.kmp.network.models.discover


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DiscoverResponse(
    @SerialName("page")
    val page: Int,
    @SerialName("results")
    val results: List<DiscoverItem>,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("total_results")
    val totalResults: Int
)