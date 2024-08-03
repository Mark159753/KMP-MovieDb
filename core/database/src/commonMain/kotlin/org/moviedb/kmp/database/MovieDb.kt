package org.moviedb.kmp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.moviedb.kmp.database.converter.IntListConverter
import org.moviedb.kmp.database.converter.KnowForConverter
import org.moviedb.kmp.database.converter.StringListConverter
import org.moviedb.kmp.database.dao.AllTrendingDao
import org.moviedb.kmp.database.dao.DiscoverDao
import org.moviedb.kmp.database.dao.GenresDao
import org.moviedb.kmp.database.dao.PersonDao
import org.moviedb.kmp.database.dao.RemoteKeyDao
import org.moviedb.kmp.database.dao.TopRatedMovieDao
import org.moviedb.kmp.database.dao.TvTrendingDao
import org.moviedb.kmp.database.dao.UpcomingDao
import org.moviedb.kmp.database.entities.*

@Database(
    version = 1,
    exportSchema = false,
    entities = [
        TopRatedMovieEntity::class,
        AllTrendingEntity::class,
        UpcomingEntity::class,
        RemoteKeyEntity::class,
        TvTrendingEntity::class,
        PersonEntity::class,
        GenreEntity::class,
        DiscoverEntity::class,
    ]
)
@TypeConverters(
    IntListConverter::class,
    StringListConverter::class,
    KnowForConverter::class
    )
abstract class MovieDb: RoomDatabase(), DB {

    companion object{
        fun createDataBase(builder:Builder<MovieDb>):MovieDb{
            return builder
                .setDriver(BundledSQLiteDriver())
                .fallbackToDestructiveMigration(true)
                .setQueryCoroutineContext(Dispatchers.IO)
                .build()
        }
    }

    abstract fun getTopRatedMovieDaoDao(): TopRatedMovieDao
    abstract fun getAllTrendingDao(): AllTrendingDao
    abstract fun getUpcomingDao(): UpcomingDao
    abstract fun getRemoteKeyDao(): RemoteKeyDao
    abstract fun getTvTrendingDao(): TvTrendingDao
    abstract fun getPersonDao(): PersonDao
    abstract fun getGenresDao(): GenresDao
    abstract fun getDiscoverDao(): DiscoverDao

    override fun clearAllTables() {}
}

// FIXME: Added a hack to resolve below issue:
// Class 'MovieDb_Impl' is not abstract and does not implement abstract base class member 'clearAllTables'.
interface DB {
    fun clearAllTables() {}
}