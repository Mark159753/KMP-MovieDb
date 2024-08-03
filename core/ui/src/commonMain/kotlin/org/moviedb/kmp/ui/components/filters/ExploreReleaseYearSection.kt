package org.moviedb.kmp.ui.components.filters

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.icerock.moko.resources.compose.stringResource
import org.moviedb.kmp.common.CommonRes
import org.moviedb.kmp.domain.states.explore.filter.ReleaseYearStateFilter
import org.moviedb.kmp.ui.theme.localColors
import org.moviedb.kmp.ui.theme.localTypography

@Composable
fun ExploreReleaseYearSection(
    modifier: Modifier = Modifier,
    state:ReleaseYearStateFilter
){

    val useYearFilter by state.useReleaseYearFilter.collectAsState()
    val selectedYear by state.releaseYear.collectAsState()

    Column(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(CommonRes.strings.explore_release_year_title),
                style = MaterialTheme.localTypography.h2,
                color = MaterialTheme.localColors.onSurface
            )

            Spacer(Modifier.weight(1f))

            Switch(
                checked = useYearFilter,
                onCheckedChange = state::onUseReleaseYearFilter,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = MaterialTheme.localColors.primary,
                    uncheckedTrackColor = MaterialTheme.localColors.shimmer
                )
            )
        }

        AnimatedVisibility(
            visible = useYearFilter
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.height(10.dp))

                Text(
                    text = selectedYear.toString(),
                    style = MaterialTheme.localTypography.bodyMedium,
                    color = MaterialTheme.localColors.onSurface
                )

                Spacer(Modifier.height(4.dp))

                Slider(
                    value = selectedYear.toFloat(),
                    onValueChange = remember {
                        {
                            state.onReleaseYearChanged(it.toInt())
                        }
                    },
                    colors = SliderDefaults.colors(
                        thumbColor = MaterialTheme.localColors.primary,
                        activeTrackColor = MaterialTheme.localColors.primary,
                        inactiveTrackColor = MaterialTheme.localColors.shimmer,
                    ),
                    steps = 1,
                    valueRange = 1930f..2024f
                )
            }
        }
    }
}