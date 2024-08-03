package org.moviedb.kmp.domain.states.explore.filter

import androidx.compose.runtime.Stable
import co.touchlab.kermit.Logger
import dev.icerock.moko.resources.StringResource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.moviedb.kmp.common.CommonRes
import org.moviedb.kmp.common.constants.ContentTypePath

@Stable
interface ContentTypeState:SaveableFilter<ContentTypePath, SortFilterItem?> {

    val contentType:StateFlow<ContentType>

    fun onSelectContentType(type: ContentType)

    fun onSelectContentType(type: SortFilterItem)

    fun isContentTypeSelected(item: ContentType) = item == contentType.value


}

enum class ContentType(
    val res:StringResource
){
    Movie(
        res = CommonRes.strings.explore_sort_movie
    ),
    Tv(
        res = CommonRes.strings.explore_sort_tv
    );

    fun toSortFilterItem() = SortFilterItem(
        displayNameResource = res,
        value = name
    )
}

@Stable
class ContentTypeStateImpl: ContentTypeState{

    private var savedContentType = ContentType.Movie

    private val _contentType = MutableStateFlow(savedContentType)
    override val contentType: StateFlow<ContentType>
        get() = _contentType

    override fun onSelectContentType(type: ContentType) {
        _contentType.value = type
    }

    override fun onSelectContentType(type: SortFilterItem) {
        try {
            _contentType.value = ContentType.valueOf(type.value)
        }catch (e:Exception){
            Logger.e(tag = "ContentTypeState", throwable = e, messageString = e.stackTraceToString())
        }
    }


    /**
     * SaveableFilter
     * */

    override fun clear() {
        savedContentType = ContentType.Movie
        _contentType.value = savedContentType
    }

    override fun initialize() {
        _contentType.value = savedContentType
    }

    override fun onGetSavedValue() = ContentTypePath.valueOf(savedContentType.name)

    override fun saveAndApplyFilter():SortFilterItem{
        savedContentType = _contentType.value
        return SortFilterItem(
            displayNameResource = contentType.value.res,
            value = contentType.value.name
        )
    }
}