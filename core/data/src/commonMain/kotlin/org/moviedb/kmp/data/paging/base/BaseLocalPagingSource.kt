package org.moviedb.kmp.data.paging.base

import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadParams.Append
import androidx.paging.PagingSource.LoadParams.Prepend
import androidx.paging.PagingSource.LoadParams.Refresh
import androidx.paging.PagingState
import androidx.room.InvalidationTracker
import androidx.room.immediateTransaction
import androidx.room.useWriterConnection
import org.moviedb.kmp.database.MovieDb
import org.moviedb.kmp.database.dao.BaseDao
import co.touchlab.stately.concurrency.AtomicInt

abstract class BaseLocalPagingSource<Value : Any, Entity: Any>(
    private val db: MovieDb,
    private val table:String,
    private val dao: BaseDao<Entity>
):PagingSource<Int, Value>(){
    private val observer =
        object : InvalidationTracker.Observer(arrayOf(table)) {
            override fun onInvalidated(tables: Set<String>) {
                if (tables.contains(table)){
                    this@BaseLocalPagingSource.invalidate()
                }
            }
        }

    private var itemCount:AtomicInt = AtomicInt(INITIAL_ITEM_COUNT)

    abstract fun toModel(entity: Entity):Value

    override fun getRefreshKey(state: PagingState<Int, Value>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Value> {
        db.invalidationTracker.subscribe(observer)
        val tempCount = itemCount.get()
        // if itemCount is < 0, then it is initial load
        return if (tempCount == INITIAL_ITEM_COUNT) {
            initialLoad(params)
        } else {
            nonInitialLoad(params, tempCount)
        }
    }

    private suspend fun initialLoad(params: LoadParams<Int>): LoadResult<Int, Value> {
        return db.useWriterConnection { transactor ->
            transactor.immediateTransaction {
                val count = dao.itemCount()
                itemCount.set(count)

                val key = params.key ?: 0
                val limit: Int = getLimit(params, key)
                val offset: Int = getOffset(params, key, itemCount.get())

                val data = dao.getAllItems(
                    limit = limit,
                    offset = offset
                )

                val nextPosToLoad = offset + data.size
                val nextKey =
                    if (data.isEmpty() || data.size < limit || nextPosToLoad >= itemCount.get()) {
                        null
                    } else {
                        nextPosToLoad
                    }
                val prevKey = if (offset <= 0 || data.isEmpty()) null else offset

                return@immediateTransaction LoadResult.Page(
                    data = data.map { toModel(it) },
                    prevKey = prevKey,
                    nextKey = nextKey,
                    itemsBefore = offset,
                    itemsAfter = maxOf(0, itemCount.get() - nextPosToLoad)
                )
            }
        }
    }

    private suspend fun nonInitialLoad(
        params: LoadParams<Int>,
        itemCount: Int,
    ): LoadResult<Int, Value> {
        val key = params.key ?: 0
        val limit: Int = getLimit(params, key)
        val offset: Int = getOffset(params, key, itemCount)

        val data = dao.getAllItems(
            limit = limit,
            offset = offset
        )

        val nextPosToLoad = offset + data.size
        val nextKey =
            if (data.isEmpty() || data.size < limit || nextPosToLoad >= itemCount) {
                null
            } else {
                nextPosToLoad
            }
        val prevKey = if (offset <= 0 || data.isEmpty()) null else offset

        val loadResult = LoadResult.Page(
            data = data.map { toModel(it) },
            prevKey = prevKey,
            nextKey = nextKey,
            itemsBefore = offset,
            itemsAfter = maxOf(0, itemCount - nextPosToLoad)
        )

        db.invalidationTracker.refreshAsync()
        return if (invalid) LoadResult.Invalid() else loadResult
    }

    private fun getOffset(params: LoadParams<Int>, key: Int, itemCount: Int): Int {
        return when (params) {
            is Prepend ->
                if (key < params.loadSize) {
                    0
                } else {
                    key - params.loadSize
                }
            is Append -> key
            is Refresh ->
                if (key >= itemCount) {
                    maxOf(0, itemCount - params.loadSize)
                } else {
                    key
                }
        }
    }

    private fun getLimit(params: LoadParams<Int>, key: Int): Int {
        return when (params) {
            is Prepend ->
                if (key < params.loadSize) {
                    key
                } else {
                    params.loadSize
                }
            else -> params.loadSize
        }
    }

}

private const val INITIAL_ITEM_COUNT = -1