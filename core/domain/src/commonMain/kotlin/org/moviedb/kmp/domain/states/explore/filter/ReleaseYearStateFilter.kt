package org.moviedb.kmp.domain.states.explore.filter

import androidx.compose.runtime.Stable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.moviedb.kmp.common.constants.ExploreParam

interface ReleaseYearStateFilter:SaveableFilter<ExploreParam, SortFilterItem?> {

    val releaseYear:StateFlow<Int>

    val useReleaseYearFilter:StateFlow<Boolean>

    fun onReleaseYearChanged(year:Int)

    fun onUseReleaseYearFilter(use:Boolean)

}

@Stable
class ReleaseYearStateFilterImpl: ReleaseYearStateFilter{

    private var savedReleaseYear:Int = 2010
    private var savedUseReleaseYearFilter:Boolean = false

    private val _releaseYear = MutableStateFlow(savedReleaseYear)
    override val releaseYear: StateFlow<Int>
        get() = _releaseYear

    private val _useReleaseYearFilter = MutableStateFlow(savedUseReleaseYearFilter)
    override val useReleaseYearFilter: StateFlow<Boolean>
        get() = _useReleaseYearFilter

    override fun onReleaseYearChanged(year: Int) {
        _releaseYear.value = year
    }

    override fun onUseReleaseYearFilter(use: Boolean) {
        _useReleaseYearFilter.value = use
    }


    /**
     * SaveableFilter
     * */

    override fun initialize() {
        _releaseYear.value = savedReleaseYear
        _useReleaseYearFilter.value = savedUseReleaseYearFilter
    }

    override fun saveAndApplyFilter(): SortFilterItem? {
        savedReleaseYear = releaseYear.value
        savedUseReleaseYearFilter = useReleaseYearFilter.value
        return if (useReleaseYearFilter.value)
            SortFilterItem(
                displayName = releaseYear.value.toString(),
                value = releaseYear.value.toString()
            )
        else null
    }

    override fun onGetSavedValue() = if (savedUseReleaseYearFilter){
        ExploreParam.ReleaseYear(value = savedReleaseYear.toString())
    }else null

    override fun clear() {
        savedReleaseYear = 2010
        savedUseReleaseYearFilter = false
        _useReleaseYearFilter.value = savedUseReleaseYearFilter
        _releaseYear.value = savedReleaseYear
    }
}