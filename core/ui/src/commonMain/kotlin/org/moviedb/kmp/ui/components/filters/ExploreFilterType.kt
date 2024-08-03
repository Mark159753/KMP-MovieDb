package org.moviedb.kmp.ui.components.filters

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.icerock.moko.resources.compose.stringResource
import org.moviedb.kmp.common.CommonRes
import org.moviedb.kmp.domain.states.explore.filter.ContentType
import org.moviedb.kmp.domain.states.explore.filter.ContentTypeState
import org.moviedb.kmp.ui.theme.localColors
import org.moviedb.kmp.ui.theme.localTypography

@Composable
fun ExploreFilterType(
    modifier: Modifier = Modifier,
    state: ContentTypeState
){
    val selectedItem by state.contentType.collectAsState()
    Column(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Text(
            text = stringResource(CommonRes.strings.explore_content_type_title),
            style = MaterialTheme.localTypography.h2,
            color = MaterialTheme.localColors.onSurface
        )

        Spacer(Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ContentType.entries.forEach { type ->
                ExploreFilterChip(
                    item = type.toSortFilterItem(),
                    selected = selectedItem == type,
                    onClick = state::onSelectContentType
                )
            }
        }
    }
}