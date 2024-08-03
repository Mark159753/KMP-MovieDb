package org.moviedb.kmp.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import org.moviedb.kmp.database.entities.DiscoverEntity

@Dao
interface DiscoverDao:BaseDao<DiscoverEntity> {

    @Query("SELECT * FROM discovers_tab ORDER BY pagingOrder ASC LIMIT :limit OFFSET :offset")
    override suspend fun getAllItems(limit: Int, offset: Int): List<DiscoverEntity>

    @Transaction
    @Query("DELETE FROM discovers_tab")
    override suspend fun deleteAllItems()

    @Query("SELECT COUNT(*) FROM discovers_tab")
    override suspend fun itemCount():Int
}