package org.moviedb.kmp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import dev.icerock.moko.resources.compose.painterResource
import org.moviedb.kmp.common.constants.PosterSize
import org.moviedb.kmp.common.constants.toPosterPath
import org.moviedb.kmp.common.exstentions.roundToRating
import org.moviedb.kmp.data.models.movie.MovieModel
import org.moviedb.kmp.ui.MR
import org.moviedb.kmp.ui.theme.localColors
import org.moviedb.kmp.ui.theme.localTypography

@Composable
fun MovieWithBackdropItem(
    modifier: Modifier = Modifier,
    item:MovieModel?
){
    Column(
        modifier = modifier
            .width(IntrinsicSize.Min)
            .height(IntrinsicSize.Min),
    ) {
        AsyncImage(
            model = item?.backdropPath
                ?.toPosterPath(PosterSize.W500),
            contentDescription = null,
            modifier = Modifier
                .clip(RoundedCornerShape(6.dp))
                .background(MaterialTheme.localColors.shimmer)
                .width(248.dp)
                .height(116.dp),
            contentScale = ContentScale.Crop,
        )

        Spacer(modifier = Modifier.height(4.dp))

        MovieWithDetailsItem(
            item = item
        )
    }
}

@Composable
fun MovieWithDetailsItem(
    modifier: Modifier = Modifier,
    item: MovieModel?
){
    Row(
        modifier = modifier
    ) {
        AsyncImage(
            model = item?.posterPath
                ?.toPosterPath(PosterSize.W500),
            contentDescription = null,
            modifier = Modifier
                .clip(RoundedCornerShape(6.dp))
                .background(MaterialTheme.localColors.shimmer)
                .width(106.dp)
                .height(152.dp),
            contentScale = ContentScale.Crop,
        )

        Spacer(modifier = Modifier.width(4.dp))

        Column(
            modifier = Modifier
                .width(134.dp)
        ) {

            var lineCount by remember { mutableIntStateOf(5) }

            Text(
                text = item?.title ?: "",
                style = MaterialTheme.localTypography.h3,
                color = MaterialTheme.localColors.onSurface,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
                onTextLayout = {textLayoutResult: TextLayoutResult ->
                    lineCount = if (textLayoutResult.lineCount > 1) 5 else 6
                }
            )

            Spacer(Modifier.height(4.dp))

            Row {
                Icon(
                    painter = painterResource(MR.images.star_rate_ic),
                    modifier = Modifier.size(16.dp),
                    contentDescription = null,
                    tint = MaterialTheme.localColors.primary
                )

                Spacer(Modifier.width(4.dp))

                Text(
                    text = item?.voteAverage?.roundToRating()?.toString() ?: "",
                    color = MaterialTheme.localColors.onSurface,
                    style = MaterialTheme.localTypography.bodySmall
                )

                item?.releaseDate?.let {
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = it,
                        color = MaterialTheme.localColors.onSurface,
                        style = MaterialTheme.localTypography.bodySmall
                    )
                }
            }

            item?.overview?.let {
                Spacer(Modifier.height(6.dp))

                Text(
                    text = it,
                    color = MaterialTheme.localColors.onSurface,
                    style = MaterialTheme.localTypography.bodySmall,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = lineCount,
                )
            }

        }
    }
}