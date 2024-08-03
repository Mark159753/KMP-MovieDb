package org.moviedb.kmp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.setSingletonImageLoaderFactory
import org.moviedb.kmp.ui.coil.getAsyncImageLoader

@OptIn(ExperimentalCoilApi::class)
@Composable
fun MovieDbTheme(
    isDarkTheme:Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
){

    setSingletonImageLoaderFactory { context ->
        getAsyncImageLoader(context)
    }

    val localColors = if (isDarkTheme)
        darkLocalColors
    else lightLocalColors

    CompositionLocalProvider(
        LocalColors provides localColors,
            LocalTypography provides AppTypography()
    ) {
        MaterialTheme(
            content = content
        )
    }

}