package org.moviedb.kmp.common.constants

enum class TimeWindow{
    Day, Week
}

enum class ContentTypePath{
    Movie, Tv
}

sealed interface ExploreParam{
    // Http parameter name
    val key:String
    // Http parameter value
    val value:String

    enum class SortBy(
        override val key: String = "sort_by",
        override val value: String
    ):ExploreParam{
        Popularity(value = "popularity.desc"),
        Revenue(value = "revenue.desc"),
        PrimaryReleaseDate(value = "primary_release_date.desc"),
        VoteAverage(value = "vote_average.desc"),
        OriginalTitle(value = "original_title.desc"),
    }

    data class ReleaseYear(
        override val key: String = "primary_release_year",
        override val value: String // Int
    ):ExploreParam

    data class OriginCountry(
        override val key: String = "with_origin_country",
        override val value: String // Country Code
    ):ExploreParam

    data class Genres(
        override val key: String = "with_genres",
        override val value: String // Separate list by comma
    ):ExploreParam

}