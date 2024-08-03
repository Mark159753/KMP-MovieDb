package org.moviedb.kmp.domain.states.explore.filter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.compose.stringResource

@Immutable
data class SortFilterItem(
    val displayNameResource:StringResource? = null,
    val displayName:String? = null,
    val value:String,
)

@Composable
fun SortFilterItem.getDisplayNameCompose(): String? {
    return when{
        displayName != null -> displayName
        displayNameResource != null -> stringResource(displayNameResource)
        else -> null
    }
}
