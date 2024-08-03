package org.moviedb.kmp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import org.moviedb.kmp.ui.theme.localColors
import org.moviedb.kmp.ui.theme.localTypography

@Composable
fun MovieDbButton(
    modifier: Modifier = Modifier,
    title:String,
    onCLick:()->Unit = {},
    colors:MovieDbButtonColors = movieDbButtonColors()
){
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(6.dp))
            .background(colors.container)
            .clickable(onClick = onCLick)
            .padding(vertical = 6.dp, horizontal = 12.dp),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = title,
            style = MaterialTheme.localTypography.bodyMedium,
            color = colors.title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun movieDbButtonColors(
    container:Color = MaterialTheme.localColors.primary,
    title:Color = Color.Black,
) = MovieDbButtonColors(
    container = container,
    title = title
)

@Composable
fun movieDbButtonColorsVariant(
    container:Color = MaterialTheme.localColors.secondary,
    title:Color = Color.White,
) = MovieDbButtonColors(
    container = container,
    title = title
)

@Immutable
data class MovieDbButtonColors(
    val container:Color,
    val title:Color,
)