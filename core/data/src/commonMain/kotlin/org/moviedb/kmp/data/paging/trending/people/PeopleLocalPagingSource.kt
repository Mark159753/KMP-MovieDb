package org.moviedb.kmp.data.paging.trending.people

import org.moviedb.kmp.data.mappers.toModel
import org.moviedb.kmp.data.models.trending.PersonModel
import org.moviedb.kmp.data.paging.base.BaseLocalPagingSource
import org.moviedb.kmp.database.MovieDb
import org.moviedb.kmp.database.entities.PersonEntity

class PeopleLocalPagingSource(
    db: MovieDb
): BaseLocalPagingSource<PersonModel, PersonEntity>(db, PersonEntity.TABLE_NAME, db.getPersonDao()) {

    override fun toModel(entity: PersonEntity) = entity.toModel()
}