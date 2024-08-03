package org.moviedb.kmp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.moviedb.kmp.common.constants.ProfileSize
import org.moviedb.kmp.common.constants.toProfilePath
import org.moviedb.kmp.data.models.trending.PersonModel
import org.moviedb.kmp.ui.theme.localColors
import org.moviedb.kmp.ui.theme.localTypography

@Composable
fun PersonItemView(
    modifier: Modifier = Modifier,
    item:PersonModel?,
){
    Column(
        modifier = modifier
            .width(90.dp)
            .height(130.dp)
    ) {
        AsyncImage(
            model = item?.profilePath
                ?.toProfilePath(ProfileSize.W185),
            contentDescription = null,
            modifier = Modifier
                .clip(RoundedCornerShape(100))
                .background(MaterialTheme.localColors.shimmer)
                .size(80.dp),
            contentScale = ContentScale.Crop,
        )

        Spacer(Modifier.height(6.dp))


        Text(
            text = item?.name ?: "",
            style = MaterialTheme.localTypography.h3,
            color = MaterialTheme.localColors.onSurface,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2,
            textAlign = TextAlign.Center
        )
    }
}