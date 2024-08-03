package org.moviedb.kmp.explore.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.cash.paging.compose.collectAsLazyPagingItems
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import org.moviedb.kmp.domain.actions.ExploreActions
import org.moviedb.kmp.domain.states.explore.filter.ExploreFilterState
import org.moviedb.kmp.ui.MR
import org.moviedb.kmp.ui.components.ExploreMovieItem
import org.moviedb.kmp.ui.components.LifeCycleActions
import org.moviedb.kmp.ui.components.LoadingView
import org.moviedb.kmp.ui.components.filters.ExploreFilterChip
import org.moviedb.kmp.ui.theme.MovieDbTheme
import org.moviedb.kmp.ui.theme.localColors
import org.moviedb.kmp.ui.theme.localTypography

@Composable
fun ExploreRoute(
    viewModel: ExploreViewModel = koinViewModel(),
    contentPadding: PaddingValues = PaddingValues(),
    onNavToFilter:()->Unit = {}
){

    ExploreScreen(
        contentPadding = contentPadding,
        onNavToFilter = onNavToFilter,
        state = viewModel.state
    )
}

@Composable
private fun ExploreScreen(
    contentPadding: PaddingValues = PaddingValues(),
    onNavToFilter:()->Unit = {},
    state: ExploreFilterState = koinInject()
){

    val statusBarPaddings = WindowInsets.statusBars.asPaddingValues()
    val pagingList = state.pagingItems.collectAsLazyPagingItems()
    val filters by state.selectedParams.collectAsState()
    val isLoading by remember {
        derivedStateOf{ pagingList.itemCount == 0 }
    }

    LifeCycleActions(flow = state.actions) { action ->
        when(action){
            ExploreActions.Retry -> pagingList.refresh()
            else -> {}
        }
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.localColors.background)
            .padding(top = 16.dp + statusBarPaddings.calculateTopPadding())
    ) {
        HeaderItem(
            onFilterClick = onNavToFilter,
            modifier = Modifier
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                top = 2.dp,
                bottom = 2.dp,
                start = 16.dp,
                end = 16.dp
            ),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(
                count = filters.size,
                key = { index -> filters[index].value }
            ){ index ->
                val item = filters[index]
                ExploreFilterChip(
                    item = item,
                    selected = false
                )
            }
        }

        if (isLoading){
            LoadingView(
                modifier = Modifier.fillMaxSize()
            )
        }else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                contentPadding = PaddingValues(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = contentPadding.calculateBottomPadding(),
                    top = 16.dp
                ),
                verticalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                items(
                    count = pagingList.itemCount,
                    key = { index -> pagingList.peek(index)?.id ?: index },
                ) { index ->
                    val item = pagingList[index]
                    ExploreMovieItem(
                        item = item
                    )

                    if (index != pagingList.itemCount -1){
                        Spacer(modifier = Modifier.height(6.dp))
                        Divider(
                            modifier = Modifier.fillMaxWidth(),
                            color = MaterialTheme.localColors.shimmer,
                        )
                    }
                }
            }
        }

    }
}

@Composable
private fun HeaderItem(
    modifier: Modifier = Modifier,
    onFilterClick:()->Unit = {}
){
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(resource = MR.strings.explore_title),
            style = MaterialTheme.localTypography.h0,
            color = MaterialTheme.localColors.onSurface
        )

        Spacer(modifier = Modifier.weight(1f))

        IconButton(onClick = onFilterClick) {
            Icon(
                painter = painterResource(imageResource = MR.images.filter_alt),
                contentDescription = "filter_btn",
                tint = MaterialTheme.localColors.onSurface
            )
        }
    }
}

@Preview
@Composable
private fun ExploreScreenPreview(){
    MovieDbTheme {
        ExploreScreen()
    }
}