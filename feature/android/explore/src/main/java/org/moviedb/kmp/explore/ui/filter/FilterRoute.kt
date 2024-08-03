package org.moviedb.kmp.explore.ui.filter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.icerock.moko.resources.compose.stringResource
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import org.moviedb.kmp.domain.states.explore.filter.ExploreFilterState
import org.moviedb.kmp.explore.ui.ExploreViewModel
import org.moviedb.kmp.ui.MR
import org.moviedb.kmp.ui.components.MovieDbButton
import org.moviedb.kmp.ui.components.filters.ExploreFilterType
import org.moviedb.kmp.ui.components.filters.ExploreGenresFilter
import org.moviedb.kmp.ui.components.filters.ExploreReleaseYearSection
import org.moviedb.kmp.ui.components.filters.ExploreSortSection
import org.moviedb.kmp.ui.components.movieDbButtonColorsVariant
import org.moviedb.kmp.ui.theme.MovieDbTheme
import org.moviedb.kmp.ui.theme.localColors
import org.moviedb.kmp.ui.theme.localTypography

@Composable
fun FilterRoute(
    viewModel: ExploreViewModel = koinViewModel(),
    onNavBack:()->Unit = {}
){

    LaunchedEffect(key1 = Unit) {
        viewModel.state.onInitialize()
    }

    FilterScreen(
        onNavBack = onNavBack,
        state = viewModel.state
    )

}

@Composable
private fun FilterScreen(
    onNavBack:()->Unit = {},
    state: ExploreFilterState = koinInject()
){

    val windowInsets = WindowInsets.statusBars

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.localColors.background)
    ){
        TopAppBar(title = {
            Text(
                text = stringResource(resource = MR.strings.explore_filer_title),
                style = MaterialTheme.localTypography.h1,
                color = MaterialTheme.localColors.onPrimaryContainerVariant,
                textAlign = TextAlign.Center,
            )
        },
            navigationIcon = {
                IconButton(
                    onClick = onNavBack
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = "back_arrow"
                    )
                }
            },
            backgroundColor = MaterialTheme.localColors.surface,
            contentColor = MaterialTheme.localColors.onPrimaryContainerVariant,
            modifier = Modifier,
            windowInsets = windowInsets,
        )

        LazyColumn(
            modifier = Modifier
                .weight(1f),
            contentPadding = PaddingValues(horizontal = 16.dp),
        ) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
                ExploreFilterType(
                    state = state.contentTypeState
                )
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                ExploreSortSection(
                    state = state.sortState
                )
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                ExploreReleaseYearSection(
                    state = state.releaseYearStateFilter
                )
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                ExploreGenresFilter(
                    state = state.genreState
                )
            }
        }

        Row(
            modifier = Modifier
                .navigationBarsPadding()
                .padding(top = 4.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            MovieDbButton(
                title = stringResource(resource = MR.strings.explore_filer_clear_btn_title),
                colors = movieDbButtonColorsVariant(),
                modifier = Modifier.weight(1f),
                onCLick = {
                    state.onClear()
                    onNavBack()
                }
            )

            Spacer(modifier = Modifier.width(8.dp))

            MovieDbButton(
                title = stringResource(resource = MR.strings.explore_filer_apply_btn_title),
                modifier = Modifier.weight(1f),
                onCLick = {
                    state.onApply()
                    onNavBack()
                }
            )
        }

    }
}

@Preview
@Composable
private fun FilterRoutePreview(){
    MovieDbTheme {
        FilterScreen()
    }
}