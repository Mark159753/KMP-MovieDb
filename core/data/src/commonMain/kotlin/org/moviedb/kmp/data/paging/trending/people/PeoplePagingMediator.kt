package org.moviedb.kmp.data.paging.trending.people

import org.moviedb.kmp.common.ApiResponse
import org.moviedb.kmp.data.mappers.toEntity
import org.moviedb.kmp.data.models.trending.PersonModel
import org.moviedb.kmp.data.paging.base.BasePagingMediator
import org.moviedb.kmp.database.MovieDb
import org.moviedb.kmp.database.entities.PersonEntity
import org.moviedb.kmp.database.entities.REMOTE_KEY_PEOPLE
import org.moviedb.kmp.database.entities.RemoteKeyEntity
import org.moviedb.kmp.network.client.ApiService
import org.moviedb.kmp.network.client.GenericResponse
import org.moviedb.kmp.network.models.trending.people.PersonItem

class PeoplePagingMediator(
    private val apiService: ApiService,
    private val db: MovieDb
): BasePagingMediator<PersonModel, PersonEntity, PersonItem>(db.getPersonDao(), db.getRemoteKeyDao(), REMOTE_KEY_PEOPLE) {

    override suspend fun apiCall(page: Int): GenericResponse<List<PersonItem>> {
        return when(val response = apiService.getPeopleTrending(page = page)){
            is ApiResponse.Error -> response
            is ApiResponse.Success -> ApiResponse.Success(response.body.results)
        }
    }


    override fun toRemoteKey(
        prevKey: Int?,
        nextKey: Int?,
        item: PersonItem
    )  = RemoteKeyEntity("${item.id}_$REMOTE_KEY_PEOPLE", prevKey, nextKey, REMOTE_KEY_PEOPLE)

    override fun toEntity(item: PersonItem, order: Double?) = item.toEntity(order)

    override suspend fun findRemoteKey(item: PersonModel?) = item?.id?.let {
        db.getRemoteKeyDao().getItemById("${item.id}_$REMOTE_KEY_PEOPLE")
    }
}