package org.moviedb.kmp.domain.states.explore.filter

import androidx.compose.runtime.Stable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.moviedb.kmp.common.ApiResponse
import org.moviedb.kmp.common.constants.ContentTypePath
import org.moviedb.kmp.common.constants.ExploreParam
import org.moviedb.kmp.data.models.genres.GenreModel
import org.moviedb.kmp.data.repositories.genre.GenreRepository
import org.moviedb.kmp.domain.actions.UIActions

interface FilterGenreState: SaveableFilter<ExploreParam, List<SortFilterItem>> {

    val genres:StateFlow<List<SortFilterItem>>
    val selectedGenres:StateFlow<Set<SortFilterItem>>

    fun onSelectGenre(genre:SortFilterItem)

    fun isGenreSelected(genre: SortFilterItem):Boolean

}

@Stable
class FilterGenreStateImpl(
    private val repository: GenreRepository,
    private val scope: CoroutineScope,
    private val actions:UIActions,
    contentTypeState: ContentTypeState,
): FilterGenreState {

    override val genres: StateFlow<List<SortFilterItem>> = contentTypeState.contentType
        .flatMapLatest { type ->
            clear()
            if (type == ContentType.Movie){
                fetchGenres(ContentTypePath.Movie)
                repository.movieGenres
            }else {
                fetchGenres(ContentTypePath.Tv)
                repository.tvGenres
            }

        }
        .map { list ->
            list.map { it.toSortFilterItem() }
        }
        .stateIn(
            scope = scope,
            initialValue = emptyList(),
            started = SharingStarted.Lazily
        )

    private var savedSelectedGenres:Set<SortFilterItem> = setOf()

    private val _selectedGenres = MutableStateFlow(savedSelectedGenres)
    override val selectedGenres: StateFlow<Set<SortFilterItem>>
        get() = _selectedGenres

    override fun onSelectGenre(genre: SortFilterItem) {
        val mutableSet = _selectedGenres.value.toMutableSet()
        if (isGenreSelected(genre)){
            mutableSet.remove(genre)
            _selectedGenres.value = mutableSet.toSet()
        }else{
            mutableSet.add(genre)
            _selectedGenres.value = mutableSet.toSet()
        }
    }

    override fun isGenreSelected(genre: SortFilterItem) = _selectedGenres.value.contains(genre)

    private fun fetchGenres(type: ContentTypePath){
        scope.launch {
            when(val response = repository.fetchGenres(type)){
                is ApiResponse.Error -> actions.handleApiError(response)
                is ApiResponse.Success -> {}
            }
        }
    }

    /**
     * SaveableFilter
     * */

    override fun initialize() {
        _selectedGenres.value = savedSelectedGenres
    }

    override fun onGetSavedValue() = if (savedSelectedGenres.isNotEmpty()){
        ExploreParam.Genres(value = savedSelectedGenres.map { it.value }.joinToString())
    }else null

    override fun saveAndApplyFilter(): List<SortFilterItem> {
        savedSelectedGenres = _selectedGenres.value
        return savedSelectedGenres.toList()
    }

    override fun clear() {
        savedSelectedGenres = setOf()
        _selectedGenres.value = savedSelectedGenres
    }
}

fun GenreModel.toSortFilterItem() = SortFilterItem(
    displayName = name,
    value = id.toString()
)