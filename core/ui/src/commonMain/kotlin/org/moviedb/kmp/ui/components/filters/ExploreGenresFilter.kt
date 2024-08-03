package org.moviedb.kmp.ui.components.filters

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.icerock.moko.resources.compose.stringResource
import org.moviedb.kmp.common.CommonRes
import org.moviedb.kmp.domain.states.explore.filter.FilterGenreState
import org.moviedb.kmp.ui.modifiers.animatePlacement
import org.moviedb.kmp.ui.theme.localColors
import org.moviedb.kmp.ui.theme.localTypography

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ExploreGenresFilter(
    modifier: Modifier = Modifier,
    state: FilterGenreState
){

    val genres by state.genres.collectAsState()
    val selectedGenres by state.selectedGenres.collectAsState()

    Column(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Text(
            text = stringResource(CommonRes.strings.explore_genre_title),
            style = MaterialTheme.localTypography.h2,
            color = MaterialTheme.localColors.onSurface
        )

        Spacer(Modifier.height(16.dp))

        FlowRow(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            genres.forEach { genre ->
                key(genre.value) {
                    ExploreFilterChip(
                        item = genre,
                        selected = selectedGenres.contains(genre),
                        onClick = state::onSelectGenre,
                        modifier = Modifier
                            .animatePlacement()
                    )
                }
            }
        }
    }
}