package org.moviedb.kmp.ui.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource
import org.moviedb.kmp.common.constants.PosterSize
import org.moviedb.kmp.common.constants.toPosterPath
import org.moviedb.kmp.common.exstentions.roundToRating
import org.moviedb.kmp.data.models.trending.TrendingModel
import org.moviedb.kmp.ui.MR
import org.moviedb.kmp.ui.theme.localColors
import org.moviedb.kmp.ui.theme.localTypography

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeTrendsSlider(
    modifier: Modifier = Modifier,
    list:List<TrendingModel> = emptyList(),
    pagerState: PagerState = rememberPagerState(pageCount = { list.size })
){
    Column(
        modifier = modifier
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f),
            key = { index -> list[index].id }
        ){ index ->
            val item = list[index]

            TrendingItem(
                modifier = Modifier.fillMaxSize(),
                item = item
            )
        }

        Spacer(Modifier.height(8.dp))

        Row(
            Modifier
                .height(24.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val indicatorSize by animateDpAsState(targetValue =  if (pagerState.currentPage == iteration) 8.dp else 6.dp,
                    label = "indicatorSize"
                )

                val color =
                    if (pagerState.currentPage == iteration)
                        MaterialTheme.localColors.primary
                    else
                        MaterialTheme.localColors.onPrimaryContainerVariant
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(indicatorSize)
                )
            }

        }
    }
}

@Composable
private fun TrendingItem(
    modifier: Modifier = Modifier,
    item:TrendingModel
){
    Box(
        modifier = modifier
    ){
        AsyncImage(
            model = item.posterPath
                ?.toPosterPath(PosterSize.W780),
            contentDescription = null,
            modifier = Modifier
                .background(MaterialTheme.localColors.shimmer)
                .fillMaxSize(),
            contentScale = ContentScale.Crop,
        )

        Spacer(
            modifier = modifier
                .background(Brush.verticalGradient(
                    0.0f to Color.Transparent,
                    0.7f to Color.Transparent,
                    0.96f to MaterialTheme.localColors.background,
                    1.0f to MaterialTheme.localColors.background
                ))
            .fillMaxSize())

        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = item.title ?: item.originalTitle?: item.name ?: item.originalName ?: "",
                style = MaterialTheme.localTypography.h2.copy(fontWeight = FontWeight.SemiBold),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.localColors.onSurface,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(8.dp))

            Details(
                modifier = Modifier.fillMaxWidth(),
                item = item
            )

            Spacer(Modifier.height(12.dp))

            Row {
                MovieDbButton(
                    title = stringResource(MR.strings.home_trends_slider_details),
                    onCLick = {},
                    colors = movieDbButtonColorsVariant()
                )

                Spacer(Modifier.width(16.dp))

                MovieDbButton(
                    title = stringResource(MR.strings.home_trends_slider_add_to_list),
                    onCLick = {}
                )
            }
        }
    }
}

@Composable
private fun Details(
    modifier: Modifier = Modifier,
    item: TrendingModel,
){
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally)
    ) {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(100))
                .background(Color.White)
                .padding(horizontal = 8.dp, vertical = 3.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(MR.images.star_rate_ic),
                contentDescription = null,
                modifier = Modifier.size(16.dp),
                tint = Color.Black
            )

            Spacer(Modifier.width(4.dp))

            Text(
                text = item.voteAverage.roundToRating().toString(),
                color = Color.Black,
                style = MaterialTheme.localTypography.bodySmall
            )
        }

        item.releaseDate?.take(4)?.let {
            Text(
                text = it,
                color = MaterialTheme.localColors.onSurface,
                style = MaterialTheme.localTypography.bodySmall
            )
        }
    }
}