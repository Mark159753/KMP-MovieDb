package paging

import androidx.paging.CombinedLoadStates
import androidx.paging.DifferCallback
import androidx.paging.ItemSnapshotList
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.NullPaddedList
import androidx.paging.PagingData
import androidx.paging.PagingDataDiffer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.moviedb.kmp.common.wrapper.Closeable

class IOSLazyPagingItems<T : Any>(
    private val flow: Flow<PagingData<T>>
) {

    private val mainDispatcher = Dispatchers.Main

    private val differCallback: DifferCallback = object : DifferCallback {
        override fun onChanged(position: Int, count: Int) {
            if (count > 0) {
                updateItemSnapshotList()
            }
        }

        override fun onInserted(position: Int, count: Int) {
            if (count > 0) {
                updateItemSnapshotList()
            }
        }

        override fun onRemoved(position: Int, count: Int) {
            if (count > 0) {
                updateItemSnapshotList()
            }
        }
    }

    private val pagingDataDiffer = object : PagingDataDiffer<T>(
        differCallback = differCallback,
        mainContext = mainDispatcher,
        cachedPagingData =
        if (flow is SharedFlow<PagingData<T>>) flow.replayCache.firstOrNull() else null
    ) {
        override suspend fun presentNewList(
            previousList: NullPaddedList<T>,
            newList: NullPaddedList<T>,
            lastAccessedIndex: Int,
            onListPresentable: () -> Unit
        ): Int? {
            onListPresentable()
            updateItemSnapshotList()
            return null
        }
    }

    private val _itemSnapshotList = MutableStateFlow(pagingDataDiffer.snapshot())
    val itemSnapshotList: StateFlow<ItemSnapshotList<T>>
        get() = _itemSnapshotList

    /**
     * The number of items which can be accessed.
     */
    val itemCount: Flow<Int> get() = itemSnapshotList.map { it.size }

    private fun updateItemSnapshotList() {
        _itemSnapshotList.value = pagingDataDiffer.snapshot()
    }

    operator fun get(index: Int): T? {
        pagingDataDiffer[index] // this registers the value load
        return itemSnapshotList.value[index]
    }

    fun peek(index: Int): T? {
        return itemSnapshotList.value[index]
    }

    fun retry() {
        pagingDataDiffer.retry()
    }

    fun refresh() {
        pagingDataDiffer.refresh()
    }

    private val _loadState = MutableStateFlow(pagingDataDiffer.loadStateFlow.value
        ?: CombinedLoadStates(
            refresh = InitialLoadStates.refresh,
            prepend = InitialLoadStates.prepend,
            append = InitialLoadStates.append,
            source = InitialLoadStates
        ))
    val loadState:StateFlow<CombinedLoadStates>
        get() = _loadState

    internal suspend fun collectLoadState() {
        pagingDataDiffer.loadStateFlow.filterNotNull().collect {
            _loadState.value = it
        }
    }

    internal suspend fun collectPagingData() {
        flow.collectLatest {
            pagingDataDiffer.collectFrom(it)
        }
    }
}

fun <T : Any> Flow<PagingData<T>>.collectAsIOSLazyPagingItems(
    block:(paging:IOSLazyPagingItems<T>)->Unit,
    itemCount:(Int)->Unit,
    loadStates:(CombinedLoadStates)-> Unit = {}
): Closeable {
    val job = Job()
    val lazyPagingItems = IOSLazyPagingItems(this)
    val scope = CoroutineScope(Dispatchers.Main + job)

    scope.launch {
        lazyPagingItems.itemCount.collectLatest(itemCount)
    }

    block(lazyPagingItems)

    scope.launch {
        lazyPagingItems.collectPagingData()
    }

    scope.launch {
        lazyPagingItems.collectLoadState()
        lazyPagingItems.loadState.collectLatest { state ->
            loadStates(state)
        }
    }
    return Closeable { job.cancel() }
}

private val IncompleteLoadState = LoadState.NotLoading(false)
private val InitialLoadStates = LoadStates(
    LoadState.Loading,
    IncompleteLoadState,
    IncompleteLoadState
)