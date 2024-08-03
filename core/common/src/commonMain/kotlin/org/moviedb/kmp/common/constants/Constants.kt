package org.moviedb.kmp.common.constants

const val SETTINGS_PREFERENCES = "settings_preferences.preferences_pb"
const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/"

enum class MediaType{
    Movie, Tv, Unknown
}

fun String.toMediaType():MediaType{
    return try {
        MediaType.valueOf(this.replaceFirstChar{ it.uppercaseChar() })
    }catch (e:Exception){
        MediaType.Unknown
    }
}

enum class PosterSize(
    val path:String
){
    W342("${BASE_IMAGE_URL}w342"),
    W500("${BASE_IMAGE_URL}w500"),
    W780("${BASE_IMAGE_URL}w780"),
    Original("${BASE_IMAGE_URL}original")
}

fun String.toPosterPath(size: PosterSize) = size.path + this

enum class BackdropSize(
    val path:String
){
    W300("${BASE_IMAGE_URL}w300"),
    W780("${BASE_IMAGE_URL}w780"),
    W1280("${BASE_IMAGE_URL}w1280"),
    Original("${BASE_IMAGE_URL}original")
}

fun String.toBackdropPath(size: BackdropSize) = size.path + this

enum class ProfileSize(
    val path:String
){
    W185("${BASE_IMAGE_URL}w185"),
    H632("${BASE_IMAGE_URL}h632"),
    Original("${BASE_IMAGE_URL}original")
}

fun String.toProfilePath(size: ProfileSize) = size.path + this