package org.moviedb.kmp.domain.states.explore.filter

import androidx.compose.runtime.Stable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.moviedb.kmp.common.CommonRes
import org.moviedb.kmp.common.constants.ExploreParam

@Stable
interface SortState: SaveableFilter<ExploreParam, SortFilterItem> {

    val sortBy:StateFlow<SortFilterItem>

    val sortOptions: List<SortFilterItem>
        get() = listOf(
            SortFilterItem(
                displayNameResource = CommonRes.strings.explore_sort_popularity,
                value = ExploreParam.SortBy.Popularity.name
            ),
            SortFilterItem(
                displayNameResource = CommonRes.strings.explore_sort_revenue,
                value = ExploreParam.SortBy.Revenue.name
            ),
            SortFilterItem(
                displayNameResource = CommonRes.strings.explore_sort_vote_average,
                value = ExploreParam.SortBy.VoteAverage.name
            ),
            SortFilterItem(
                displayNameResource = CommonRes.strings.explore_sort_original_title,
                value = ExploreParam.SortBy.OriginalTitle.name
            ),
            SortFilterItem(
                displayNameResource = CommonRes.strings.explore_sort_primary_release_date,
                value = ExploreParam.SortBy.PrimaryReleaseDate.name
            ),
        )

    fun onSortBy(item:SortFilterItem)

    fun isSortItemSelected(item: SortFilterItem) = item == sortBy.value
}

@Stable
class SortStateImpl:SortState{


    private var savedSortBy:SortFilterItem = sortOptions.first()

    private val _sortBy = MutableStateFlow(savedSortBy)
    override val sortBy: StateFlow<SortFilterItem>
        get() = _sortBy

    override fun onSortBy(item: SortFilterItem) {
        _sortBy.value = item
    }

    /**
     * SaveableFilter
     * */

    override fun initialize() {
        _sortBy.value = savedSortBy
    }

    override fun onGetSavedValue(): ExploreParam? {
        return try {
            ExploreParam.SortBy.valueOf(savedSortBy.value)
        }catch (e:Exception){
            null
        }
    }

    override fun saveAndApplyFilter(): SortFilterItem {
        savedSortBy = _sortBy.value
        return savedSortBy
    }

    override fun clear() {
        savedSortBy = sortOptions.first()
        _sortBy.value = savedSortBy
    }
}

