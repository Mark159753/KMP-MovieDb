package org.moviedb.kmp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import org.moviedb.kmp.database.entities.TopRatedMovieEntity

@Dao
interface TopRatedMovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(item: List<TopRatedMovieEntity>)

    @Query("SELECT * FROM top_rated_movies")
    suspend fun getAllItems():List<TopRatedMovieEntity>

    @Query("SELECT * FROM top_rated_movies WHERE id = :id")
    suspend fun getItemById(id:Int): TopRatedMovieEntity?

    @Query("SELECT * FROM top_rated_movies")
    fun getAllFlow(): Flow<List<TopRatedMovieEntity>>

    @Query("DELETE FROM top_rated_movies")
    suspend fun deleteAllItems()

}