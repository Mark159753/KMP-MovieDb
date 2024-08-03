package org.moviedb.kmp.database

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform