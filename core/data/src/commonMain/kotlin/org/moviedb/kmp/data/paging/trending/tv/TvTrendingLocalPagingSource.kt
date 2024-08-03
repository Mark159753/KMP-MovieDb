package org.moviedb.kmp.data.paging.trending.tv

import org.moviedb.kmp.data.mappers.toModel
import org.moviedb.kmp.data.models.trending.TrendingModel
import org.moviedb.kmp.data.paging.base.BaseLocalPagingSource
import org.moviedb.kmp.database.MovieDb
import org.moviedb.kmp.database.dao.TvTrendingDao
import org.moviedb.kmp.database.entities.TvTrendingEntity

class TvTrendingLocalPagingSource(
    db: MovieDb,
    dao: TvTrendingDao
): BaseLocalPagingSource<TrendingModel, TvTrendingEntity>(db, TvTrendingEntity.TABLE_NAME, dao) {

    override fun toModel(entity: TvTrendingEntity) = entity.toModel()
}