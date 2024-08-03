package org.moviedb.kmp.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import org.moviedb.kmp.database.entities.TvTrendingEntity

@Dao
interface TvTrendingDao: BaseDao<TvTrendingEntity> {

    @Query("SELECT * FROM tv_trending_table")
    suspend fun getAllItems():List<TvTrendingEntity>

    @Query("SELECT * FROM tv_trending_table ORDER BY pagingOrder ASC LIMIT :limit OFFSET :offset")
    override suspend fun getAllItems(limit:Int, offset:Int):List<TvTrendingEntity>

    @Query("SELECT * FROM tv_trending_table WHERE id = :id")
    suspend fun getItemById(id:Int): TvTrendingEntity?

    @Query("SELECT * FROM tv_trending_table")
    fun getAllFlow(): Flow<List<TvTrendingEntity>>

    @Transaction
    @Query("DELETE FROM tv_trending_table")
    override suspend fun deleteAllItems()

    @Query("SELECT COUNT(*) FROM tv_trending_table")
    override suspend fun itemCount():Int
}