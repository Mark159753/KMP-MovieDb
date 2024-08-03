package org.moviedb.kmp.ui.components.side_navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
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
import androidx.compose.ui.unit.dp
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource
import org.moviedb.kmp.ui.theme.localColors
import org.moviedb.kmp.ui.theme.localTypography

@Composable
fun MovieDbSideNavBar(
    modifier: Modifier = Modifier,
    state: MovieDbSideNavBarState = rememberMovieDbSideNavState(),
    colors: NavigationColors = sideNavigationColors()
){

    Column(
        modifier = modifier
            .background(colors.container)
            .padding(horizontal = 16.dp)
            .fillMaxHeight(),
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(Modifier.height(10.dp))

        IconButton(
            onClick = state::onExpandClick,
            modifier = Modifier.size(44.dp)
        ){
            Icon(
                imageVector = if (state.isExpanded) Icons.Default.Close else Icons.Default.Menu,
                contentDescription = null,
                tint = colors.label,
                modifier = Modifier.size(20.dp)
            )
        }

        Spacer(Modifier.fillMaxHeight(0.08f))

        state.sideBarDestinations.forEach { destination ->
            val selected = state.currentSideBarDestination == destination
            NavigationItem(
                selected = selected,
                expanded = state.isExpanded,
                onClick = { state.navigateToSideBarDestination(destination) },
                icon = painterResource(destination.icon),
                label = stringResource(resource = destination.titleText),
                colors = colors
            )
            Spacer(Modifier.height(12.dp))
        }
    }
}

@Composable
fun ColumnScope.NavigationItem(
    modifier: Modifier = Modifier,
    selected: Boolean,
    expanded: Boolean,
    onClick: () -> Unit,
    icon: Painter,
    label: String,
    colors: NavigationColors = sideNavigationColors(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
){
    val selectedColor by animateColorAsState(
        targetValue = if (selected) colors.selectedItemBg else colors.container, label = "select_bg",
        animationSpec = tween(durationMillis = 300)
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
            .padding(horizontal = 12.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = icon,
            contentDescription = null,
            modifier = Modifier.size(20.dp),
            tint = labelColor
        )
        AnimatedVisibility(visible = expanded) {
            Row {
                Spacer(modifier = Modifier.width(8.dp))
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
fun sideNavigationColors() = NavigationColors(
    container = MaterialTheme.localColors.surface,
    selectedItemBg = MaterialTheme.localColors.primary,
    selectedLabel = MaterialTheme.localColors.onPrimaryContainer,
    label = MaterialTheme.localColors.onPrimaryContainerVariant
)

@Immutable
data class NavigationColors(
    val container: Color,
    val selectedItemBg: Color,
    val selectedLabel: Color,
    val label: Color
)