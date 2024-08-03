package org.moviedb.kmp.ui.thema

import org.moviedb.kmp.ui.extensions.createDynamicUIColor
import org.moviedb.kmp.ui.theme.darkLocalColors
import org.moviedb.kmp.ui.theme.lightLocalColors
import platform.UIKit.UIColor

data class MovieDbColorSchema(
    val background: UIColor = createDynamicUIColor(lightLocalColors.background, darkLocalColors.background),
    val container: UIColor = createDynamicUIColor(lightLocalColors.container, darkLocalColors.container),
    val backgroundVariant: UIColor = createDynamicUIColor(lightLocalColors.backgroundVariant, darkLocalColors.backgroundVariant),
    val onSurface: UIColor = createDynamicUIColor(lightLocalColors.onSurface, darkLocalColors.onSurface),
    val surface: UIColor = createDynamicUIColor(lightLocalColors.surface, darkLocalColors.surface),
    val onPrimaryContainer: UIColor = createDynamicUIColor(lightLocalColors.onPrimaryContainer, darkLocalColors.onPrimaryContainer),
    val primary: UIColor = createDynamicUIColor(lightLocalColors.primary, darkLocalColors.primary),
    val onPrimaryContainerVariant: UIColor = createDynamicUIColor(lightLocalColors.onPrimaryContainerVariant, darkLocalColors.onPrimaryContainerVariant),
    val shimmer: UIColor = createDynamicUIColor(lightLocalColors.shimmer, darkLocalColors.shimmer),
    val secondary: UIColor = createDynamicUIColor(lightLocalColors.secondary, darkLocalColors.secondary),
    val surfaceVariant: UIColor = createDynamicUIColor(lightLocalColors.surfaceVariant, darkLocalColors.surfaceVariant),
    val onSurfaceVariant: UIColor = createDynamicUIColor(lightLocalColors.onSurfaceVariant, darkLocalColors.onSurfaceVariant),
    val unselectedChipBorder:UIColor = createDynamicUIColor(lightLocalColors.unselectedChipBorder, darkLocalColors.unselectedChipBorder),
    val selectedChipBorder:UIColor = createDynamicUIColor(lightLocalColors.selectedChipBorder, darkLocalColors.selectedChipBorder),
    val selectedChipBg:UIColor = createDynamicUIColor(lightLocalColors.selectedChipBg, darkLocalColors.selectedChipBg),
)

val movieDbColorSchema = MovieDbColorSchema()

