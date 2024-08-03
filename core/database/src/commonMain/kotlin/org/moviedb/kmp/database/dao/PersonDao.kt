package org.moviedb.kmp.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import org.moviedb.kmp.database.entities.PersonEntity

@Dao
interface PersonDao:BaseDao<PersonEntity> {

    @Query("SELECT * FROM person_table")
    suspend fun getAllItems():List<PersonEntity>

    @Query("SELECT * FROM person_table ORDER BY pagingOrder ASC LIMIT :limit OFFSET :offset")
    override suspend fun getAllItems(limit:Int, offset:Int):List<PersonEntity>

    @Query("SELECT * FROM person_table WHERE id = :id")
    suspend fun getItemById(id:Int): PersonEntity?

    @Query("SELECT * FROM person_table")
    fun getAllFlow(): Flow<List<PersonEntity>>

    @Transaction
    @Query("DELETE FROM person_table")
    override suspend fun deleteAllItems()

    @Query("SELECT COUNT(*) FROM person_table")
    override suspend fun itemCount():Int
}