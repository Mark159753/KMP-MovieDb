package org.moviedb.kmp.database

import androidx.room.Room
import androidx.room.RoomDatabase
import platform.Foundation.NSHomeDirectory

fun getDatabaseBuilder(): RoomDatabase.Builder<MovieDb> {
    val dbFilePath = NSHomeDirectory() + "/movie.db"
    return Room.databaseBuilder<MovieDb>(
        name = dbFilePath,
        factory =  { MovieDb::class.instantiateImpl() }
    )
}