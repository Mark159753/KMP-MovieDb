package org.moviedb.kmp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import org.moviedb.kmp.common.constants.ContentTypePath
import org.moviedb.kmp.database.entities.GenreEntity

@Dao
interface GenresDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(item: List<GenreEntity>)

    @Query("SELECT * FROM genres_tab")
    suspend fun getAllItems():List<GenreEntity>

    @Query("SELECT * FROM genres_tab WHERE id = :id")
    suspend fun getItemById(id:Int): GenreEntity?

    @Query("SELECT * FROM genres_tab")
    fun getAllFlow(): Flow<List<GenreEntity>>

    @Query("SELECT * FROM genres_tab WHERE typePath = :type")
    fun getAllByType(type: ContentTypePath): Flow<List<GenreEntity>>

    @Query("DELETE FROM genres_tab")
    suspend fun deleteAllItems()

    @Transaction
    @Query("DELETE FROM genres_tab WHERE typePath = :type")
    suspend fun deleteAllByType(type: ContentTypePath)
}