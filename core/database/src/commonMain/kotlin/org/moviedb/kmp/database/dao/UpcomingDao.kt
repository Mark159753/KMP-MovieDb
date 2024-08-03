package org.moviedb.kmp.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import org.moviedb.kmp.database.entities.UpcomingEntity

@Dao
interface UpcomingDao:BaseDao<UpcomingEntity> {

    @Query("SELECT * FROM upcoming_table")
    suspend fun getAllItems():List<UpcomingEntity>

    @Query("SELECT * FROM upcoming_table ORDER BY pagingOrder ASC LIMIT :limit OFFSET :offset")
    override suspend fun getAllItems(limit:Int, offset:Int):List<UpcomingEntity>

    @Query("SELECT * FROM upcoming_table WHERE id = :id")
    suspend fun getItemById(id:Int): UpcomingEntity?

    @Query("SELECT * FROM upcoming_table")
    fun getAllFlow(): Flow<List<UpcomingEntity>>

    @Transaction
    @Query("DELETE FROM upcoming_table")
    override suspend fun deleteAllItems()

    @Query("SELECT COUNT(*) FROM upcoming_table")
    override suspend fun itemCount():Int

    companion object{
        const val TABLE_NAME = "upcoming_table"
    }
}