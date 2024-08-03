package org.moviedb.kmp.database.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Transaction

interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: T)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entities: List<T>)

    suspend fun getAllItems(limit:Int, offset:Int):List<T>

    suspend fun deleteAllItems()

    suspend fun itemCount():Int
}