package org.moviedb.kmp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import org.moviedb.kmp.database.entities.AllTrendingEntity

@Dao
interface AllTrendingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(item: List<AllTrendingEntity>)

    @Query("SELECT * FROM all_trending_table")
    suspend fun getAllItems():List<AllTrendingEntity>

    @Query("SELECT * FROM all_trending_table WHERE id = :id")
    suspend fun getItemById(id:Int): AllTrendingEntity?

    @Query("SELECT * FROM all_trending_table")
    fun getAllFlow(): Flow<List<AllTrendingEntity>>

    @Query("DELETE FROM all_trending_table")
    suspend fun deleteAllItems()
}