package org.moviedb.kmp.home.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import dev.icerock.moko.resources.compose.stringResource
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import org.moviedb.kmp.domain.states.home.HomeState
import org.moviedb.kmp.ui.MR
import org.moviedb.kmp.ui.components.HomeTrendsSlider
import org.moviedb.kmp.ui.components.MovieWithBackdropItem
import org.moviedb.kmp.ui.components.MovieWithDetailsItem
import org.moviedb.kmp.ui.components.PersonItemView
import org.moviedb.kmp.ui.theme.MovieDbTheme
import org.moviedb.kmp.ui.theme.localColors
import org.moviedb.kmp.ui.theme.localTypography

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
    contentPadding: PaddingValues = PaddingValues()
){
    HomeView(
        state = viewModel.state,
        contentPadding = contentPadding
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun HomeView(
    modifier: Modifier = Modifier,
    state:HomeState = koinInject(),
    contentPadding: PaddingValues = PaddingValues()
){

    val trends by state.trendsList.collectAsStateWithLifecycle()
    val upcomingMovies = state.upcomingMovies.collectAsLazyPagingItems()
    val tvTrending = state.tvTrending.collectAsLazyPagingItems()
    val trendingPeople = state.trendingPeople.collectAsLazyPagingItems()

    BoxWithConstraints(
        modifier = modifier
            .background(MaterialTheme.localColors.background)
            .fillMaxSize()
    ) {
        val headerHeight = maxHeight * 0.5f

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(bottom = contentPadding.calculateBottomPadding() + 16.dp)
        ) {
            item {
                HomeTrendsSlider(
                    list = trends,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(headerHeight)
                )
            }

            item {
                SectionTitle(
                    title = stringResource(resource = MR.strings.home_screen_upcoming_title)
                )


                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) {
                    items(
                        count = upcomingMovies.itemCount,
                        key = { index -> upcomingMovies.peek(index)?.id ?: index },
                    ) { index ->
                        val item = upcomingMovies[index]
                        MovieWithBackdropItem(
                            item = item
                        )
                    }
                }
            }

            item {
                SectionTitle(
                    title = stringResource(resource = MR.strings.home_screen_tv_trends_title)
                )

                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) {
                    items(
                        count = tvTrending.itemCount,
                        key = { index -> tvTrending.peek(index)?.id ?: index },
                    ) { index ->
                        val item = tvTrending[index]
                        MovieWithDetailsItem(
                            item = item
                        )
                    }
                }
            }

            item {
                SectionTitle(
                    title = stringResource(resource = MR.strings.home_screen_people_title)
                )

                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) {
                    items(
                        count = trendingPeople.itemCount,
                        key = { index -> trendingPeople.peek(index)?.id ?: index },
                    ) { index ->
                        val item = trendingPeople[index]
                        PersonItemView(
                            item = item
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SectionTitle(
    modifier: Modifier = Modifier,
    title:String
){
    Column(
        modifier = modifier,
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = title,
            style = MaterialTheme.localTypography.h2,
            color = MaterialTheme.localColors.onSurface,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview
@Composable
private fun HomeScreenPreview(){
    MovieDbTheme {
        HomeView()
    }
}