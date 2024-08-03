package org.moviedb.kmp.data.paging.upcoming

import org.moviedb.kmp.data.mappers.toModel
import org.moviedb.kmp.data.models.movie.MovieModel
import org.moviedb.kmp.data.paging.base.BaseLocalPagingSource
import org.moviedb.kmp.database.MovieDb
import org.moviedb.kmp.database.dao.UpcomingDao
import org.moviedb.kmp.database.entities.UpcomingEntity

class UpcomingLocalPagingSource(
    db: MovieDb,
    dao: UpcomingDao
):BaseLocalPagingSource<MovieModel, UpcomingEntity>(db, UpcomingDao.TABLE_NAME, dao) {

    override fun toModel(entity: UpcomingEntity) = entity.toModel()
}