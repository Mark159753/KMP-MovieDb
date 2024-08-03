package org.moviedb.kmp.ui.components.bottom_navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource
import org.moviedb.kmp.ui.theme.MovieDbTheme
import org.moviedb.kmp.ui.theme.localColors
import org.moviedb.kmp.ui.theme.localTypography

@Composable
fun MovieDbBottomNavigationBar(
    modifier: Modifier = Modifier,
    state:MovieDbBottomNavState = rememberMovieDbBottomNavState(),
    colors: NavigationColors = bottomNavigationVColors()
){

    Row(
        modifier = modifier
            .background(colors.container)
            .navigationBarsPadding()
            .height(64.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        state.bottomBarDestinations.forEach { destination ->
            val selected = state.currentBottomBarDestination == destination
            NavigationItem(
                selected = selected,
                onClick = { state.navigateToBottomBarDestination(destination) },
                icon = painterResource(destination.icon),
                label = stringResource(resource = destination.titleText),
                colors = colors
            )
        }
    }
}

@Composable
private fun RowScope.NavigationItem(
    modifier: Modifier = Modifier,
    selected: Boolean,
    onClick: () -> Unit,
    icon: Painter,
    label: String,
    colors: NavigationColors = bottomNavigationVColors(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
){

    val selectedColor by animateColorAsState(
        targetValue = if (selected) colors.selectedItemBg else colors.container, label = "select_bg"
    )
    val labelColor by animateColorAsState(
        targetValue = if (selected) colors.selectedLabel else colors.label, label = "select_label"
    )

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(100))
            .background(selectedColor)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick,
            )
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = icon,
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = labelColor
        )
        AnimatedVisibility(visible = selected) {
            Row {
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = label,
                    style = MaterialTheme.localTypography.bodySmall.copy(fontWeight = FontWeight.SemiBold),
                    color = labelColor
                )
            }
        }
    }
}

@Composable
fun bottomNavigationVColors() = NavigationColors(
    container = MaterialTheme.localColors.surface,
    selectedItemBg = MaterialTheme.localColors.primary,
    selectedLabel = MaterialTheme.localColors.onPrimaryContainer,
    label = MaterialTheme.localColors.onPrimaryContainerVariant
)

@Immutable
data class NavigationColors(
    val container:Color,
    val selectedItemBg:Color,
    val selectedLabel:Color,
    val label:Color
)

@Preview
@Composable
private fun BottomNavigationPreview(){
    MovieDbTheme {
        MovieDbBottomNavigationBar()
    }
}
