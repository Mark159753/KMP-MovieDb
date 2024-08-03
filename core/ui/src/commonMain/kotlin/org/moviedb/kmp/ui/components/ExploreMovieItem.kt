package org.moviedb.kmp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.moviedb.kmp.common.constants.PosterSize
import org.moviedb.kmp.common.constants.toPosterPath
import org.moviedb.kmp.data.models.movie.MovieModel
import org.moviedb.kmp.ui.theme.localColors
import org.moviedb.kmp.ui.theme.localTypography

@Composable
fun ExploreMovieItem(
    modifier: Modifier = Modifier,
    item:MovieModel?
){
    Row(
        modifier = modifier
            .height(128.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = item?.posterPath
                ?.toPosterPath(PosterSize.W780),
            contentDescription = null,
            modifier = Modifier
                .background(MaterialTheme.localColors.shimmer)
                .fillMaxHeight()
                .width(84.dp),
            contentScale = ContentScale.Crop,
        )

        Spacer(Modifier.width(8.dp))

        Column {
            Text(
                text = item?.title ?: "",
                style = MaterialTheme.localTypography.h2,
                color = MaterialTheme.localColors.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(Modifier.height(4.dp))

            Text(
                text = item?.releaseDate ?: "",
                style = MaterialTheme.localTypography.bodySmall,
                color = MaterialTheme.localColors.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = item?.overview ?: "",
                style = MaterialTheme.localTypography.bodySmall,
                color = MaterialTheme.localColors.onSurface,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxHeight(),
                maxLines = 4
            )
        }
    }
}