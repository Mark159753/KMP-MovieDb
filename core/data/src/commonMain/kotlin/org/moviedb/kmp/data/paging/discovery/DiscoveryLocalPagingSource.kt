package org.moviedb.kmp.data.paging.discovery

import org.moviedb.kmp.data.mappers.toModel
import org.moviedb.kmp.data.models.movie.MovieModel
import org.moviedb.kmp.data.paging.base.BaseLocalPagingSource
import org.moviedb.kmp.database.MovieDb
import org.moviedb.kmp.database.entities.DiscoverEntity

class DiscoveryLocalPagingSource(
    db: MovieDb,
):BaseLocalPagingSource<MovieModel, DiscoverEntity>(db, DiscoverEntity.TABLE_NAME, db.getDiscoverDao()) {

    override fun toModel(entity: DiscoverEntity) = entity.toModel()
}