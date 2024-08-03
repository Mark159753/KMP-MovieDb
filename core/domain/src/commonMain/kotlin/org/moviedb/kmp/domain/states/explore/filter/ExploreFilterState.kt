package org.moviedb.kmp.domain.states.explore.filter

import androidx.compose.runtime.Stable
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import org.moviedb.kmp.common.constants.ContentTypePath
import org.moviedb.kmp.common.constants.ExploreParam
import org.moviedb.kmp.data.models.movie.MovieModel
import org.moviedb.kmp.data.repositories.discovery.DiscoveryRepository
import org.moviedb.kmp.data.repositories.genre.GenreRepository
import org.moviedb.kmp.domain.actions.ExploreActions
import org.moviedb.kmp.domain.actions.UIActionImp
import org.moviedb.kmp.domain.actions.UIActions

@Stable
interface ExploreFilterState: UIActions
{

    val selectedParams:StateFlow<List<SortFilterItem>>

    val pagingItems:Flow<PagingData<MovieModel>>

    val contentTypeState:ContentTypeState
    val releaseYearStateFilter:ReleaseYearStateFilter
    val sortState:SortState
    val genreState:FilterGenreState

    fun onInitialize()

    fun onApply()

    fun onClear()
}

@Stable
class ExploreFilterStateImpl(
    repository: GenreRepository,
    discoveryRepository:DiscoveryRepository,
    private val scope: CoroutineScope,
):
    UIActions by UIActionImp(),
    ExploreFilterState
{

    private val _selectedParams = MutableStateFlow<List<SortFilterItem>>(emptyList())
    override val selectedParams: StateFlow<List<SortFilterItem>>
        get() = _selectedParams

    override val pagingItems: Flow<PagingData<MovieModel>> = discoveryRepository
        .getDiscoveryPagingSource(this::onGetFiltersParams)
        .flowOn(Dispatchers.IO)
        .cachedIn(scope)

    override val contentTypeState:ContentTypeState = ContentTypeStateImpl()
    override val releaseYearStateFilter:ReleaseYearStateFilter = ReleaseYearStateFilterImpl()
    override val sortState:SortState = SortStateImpl()
    override val genreState:FilterGenreState = FilterGenreStateImpl(
        repository = repository,
        scope = scope,
        actions = this,
        contentTypeState = contentTypeState
    )

    init {
        onApply()
    }

    private fun onGetFiltersParams():Pair<ContentTypePath, List<ExploreParam>>{
        val contentType = contentTypeState.onGetSavedValue()!!
        val params = listOfNotNull(
            releaseYearStateFilter.onGetSavedValue(),
            sortState.onGetSavedValue(),
            genreState.onGetSavedValue()
        )
        return contentType to params
    }

    override fun onInitialize() {
        contentTypeState.initialize()
        sortState.initialize()
        releaseYearStateFilter.initialize()
        genreState.initialize()
    }

    override fun onApply() {
        val newParams = listOfNotNull(
            contentTypeState.saveAndApplyFilter(),
            sortState.saveAndApplyFilter(),
            releaseYearStateFilter.saveAndApplyFilter(),
        )
        _selectedParams.value = (newParams + genreState.saveAndApplyFilter()!!)
        scope.launch {
            sendAction(ExploreActions.Retry)
        }
    }

    override fun onClear() {
        contentTypeState.clear()
        sortState.clear()
        releaseYearStateFilter.clear()
        genreState.clear()
        onApply()
    }
}