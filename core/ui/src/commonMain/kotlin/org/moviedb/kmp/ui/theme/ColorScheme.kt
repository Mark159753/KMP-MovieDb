package org.moviedb.kmp.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.graphics.Color

@Stable
class ColorScheme(
    background:Color,
    container:Color,
    backgroundVariant: Color,
    onSurface:Color,
    surface:Color,
    onPrimaryContainer:Color,
    primary:Color,
    onPrimaryContainerVariant:Color,
    shimmer:Color,
    secondary:Color,
    surfaceVariant:Color,
    onSurfaceVariant:Color,
    unselectedChipBorder:Color,
    selectedChipBorder:Color,
    selectedChipBg:Color,
){

    var background:Color by mutableStateOf(background, structuralEqualityPolicy())
        internal set

    var container:Color by mutableStateOf(container, structuralEqualityPolicy())
        internal set

    var backgroundVariant:Color by mutableStateOf(backgroundVariant, structuralEqualityPolicy())
        internal set

    var onSurface:Color by mutableStateOf(onSurface, structuralEqualityPolicy())
        internal set

    var surface:Color by mutableStateOf(surface, structuralEqualityPolicy())
        internal set

    var onPrimaryContainer:Color by mutableStateOf(onPrimaryContainer, structuralEqualityPolicy())
        internal set

    var primary:Color by mutableStateOf(primary, structuralEqualityPolicy())
        internal set

    var onPrimaryContainerVariant:Color by mutableStateOf(onPrimaryContainerVariant, structuralEqualityPolicy())
        internal set

    var shimmer:Color by mutableStateOf(shimmer, structuralEqualityPolicy())
        internal set

    var secondary:Color by mutableStateOf(secondary, structuralEqualityPolicy())
        internal set

    var surfaceVariant:Color by mutableStateOf(surfaceVariant, structuralEqualityPolicy())
        internal set

    var onSurfaceVariant:Color by mutableStateOf(onSurfaceVariant, structuralEqualityPolicy())
        internal set

    var unselectedChipBorder:Color by mutableStateOf(unselectedChipBorder, structuralEqualityPolicy())
        internal set

    var selectedChipBorder:Color by mutableStateOf(selectedChipBorder, structuralEqualityPolicy())
        internal set

    var selectedChipBg:Color by mutableStateOf(selectedChipBg, structuralEqualityPolicy())
        internal set


    fun copy(
        background: Color = this.background,
        container:Color = this.container,
        backgroundVariant: Color = this.backgroundVariant,
        onSurface: Color = this.onSurface,
        surface: Color = this.surface,
        onPrimaryContainer: Color = this.onPrimaryContainer,
        primary: Color = this.primary,
        onPrimaryContainerVariant: Color = this.onPrimaryContainerVariant,
        shimmer: Color = this.shimmer,
        secondary:Color = this.secondary,
        surfaceVariant:Color = this.surfaceVariant,
        onSurfaceVariant:Color = this.onSurfaceVariant,
        unselectedChipBorder:Color = this.unselectedChipBorder,
        selectedChipBorder:Color = this.selectedChipBorder,
        selectedChipBg:Color = this.selectedChipBg,
    ) = ColorScheme(
        background = background,
        container = container,
        backgroundVariant = backgroundVariant,
        onSurface = onSurface,
        surface = surface,
        onPrimaryContainer = onPrimaryContainer,
        primary = primary,
        onPrimaryContainerVariant = onPrimaryContainerVariant,
        shimmer = shimmer,
        secondary = secondary,
        surfaceVariant = surfaceVariant,
        onSurfaceVariant = onSurfaceVariant,
        unselectedChipBorder = unselectedChipBorder,
        selectedChipBorder = selectedChipBorder,
        selectedChipBg = selectedChipBg,
    )
}

internal val lightLocalColors = ColorScheme(
    background = White97,
    container = Yellow54,
    backgroundVariant = Yellow75,
    onSurface = Black,
    surface = White,
    onPrimaryContainer = Black2,
    primary = Yellow51,
    onPrimaryContainerVariant = Gray28,
    shimmer = Gray30,
    secondary = Red46,
    surfaceVariant = Gray95,
    onSurfaceVariant = Black12,
    unselectedChipBorder = Gray33,
    selectedChipBorder = Black,
    selectedChipBg = Yellow51,
)

internal val darkLocalColors = ColorScheme(
    background = Black8,
    container = Black8,
    backgroundVariant = Yellow56,
    onSurface = White,
    surface = Black13,
    onPrimaryContainer = Black2,
    primary = Yellow51,
    onPrimaryContainerVariant = Gray87,
    shimmer = Gray41,
    secondary = Red46,
    surfaceVariant = Gray20,
    onSurfaceVariant = Gray92,
    unselectedChipBorder = Gray93,
    selectedChipBorder = Yellow51,
    selectedChipBg = Yellow51,
)

internal val LocalColors = staticCompositionLocalOf {
    lightLocalColors
}

val MaterialTheme.localColors: ColorScheme
    @Composable
    get() = LocalColors.current