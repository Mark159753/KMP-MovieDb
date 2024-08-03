package org.moviedb.kmp.database

import androidx.room.Room
import androidx.room.RoomDatabase
import java.io.File

fun getDatabaseBuilder(): RoomDatabase.Builder<MovieDb> {
    val dbFile = File(System.getProperty("java.io.tmpdir"), "movie.db")
    return Room.databaseBuilder<MovieDb>(
        name = dbFile.absolutePath,
    )
}