package org.moviedb.kmp.ui.components.filters

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.moviedb.kmp.domain.states.explore.filter.SortFilterItem
import org.moviedb.kmp.domain.states.explore.filter.getDisplayNameCompose
import org.moviedb.kmp.ui.theme.localColors
import org.moviedb.kmp.ui.theme.localTypography

@Composable
fun ExploreFilterChip(
    modifier: Modifier = Modifier,
    item: SortFilterItem,
    selected:Boolean,
    onClick:((SortFilterItem)->Unit)? = null
){

    val bgColor by animateColorAsState(
        if (selected) MaterialTheme.localColors.selectedChipBg
        else Color.Transparent
    )

    val borderColor by animateColorAsState(
        if (selected) MaterialTheme.localColors.selectedChipBorder
        else MaterialTheme.localColors.unselectedChipBorder
    )

    val textColor by animateColorAsState(
        if (selected) Color.Black
        else MaterialTheme.localColors.unselectedChipBorder
    )

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(100))
            .background(bgColor)
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(100)
            )
            .then(
                if (onClick == null){
                    Modifier
                }else{
                    Modifier.clickable { onClick(item) }
                }
            )
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        AnimatedVisibility(visible = selected) {
            Row {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = "Done icon",
                    modifier = Modifier.size(20.dp),
                    tint = textColor
                )
                Spacer(Modifier.width(4.dp))
            }
        }

        Text(
            item.getDisplayNameCompose() ?: "",
            style = MaterialTheme.localTypography.h3,
            color = textColor
        )
    }
}