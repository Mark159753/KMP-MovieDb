package org.moviedb.kmp.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

fun getDatabaseBuilder(ctx: Context): RoomDatabase.Builder<MovieDb> {
    val appContext = ctx.applicationContext
    val dbFile = appContext.getDatabasePath("movie.db")
    return Room.databaseBuilder<MovieDb>(
        context = appContext,
        name = dbFile.absolutePath
    )
}