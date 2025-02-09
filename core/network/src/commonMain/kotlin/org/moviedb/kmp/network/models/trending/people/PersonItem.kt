package org.moviedb.kmp.network.models.trending.people


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.moviedb.kmp.network.models.trending.all.TrendingItem

@Serializable
data class PersonItem(
    @SerialName("adult")
    val adult: Boolean,
    @SerialName("gender")
    val gender: Int,
    @SerialName("id")
    val id: Int,
    @SerialName("known_for")
    val knownFor: List<TrendingItem>,
    @SerialName("known_for_department")
    val knownForDepartment: String?,
    @SerialName("media_type")
    val mediaType: String,
    @SerialName("name")
    val name: String,
    @SerialName("original_name")
    val originalName: String,
    @SerialName("popularity")
    val popularity: Double,
    @SerialName("profile_path")
    val profilePath: String?
)