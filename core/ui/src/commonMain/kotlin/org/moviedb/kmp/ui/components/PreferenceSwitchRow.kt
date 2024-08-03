package org.moviedb.kmp.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.moviedb.kmp.ui.theme.MovieDbTheme
import org.moviedb.kmp.ui.theme.localColors
import org.moviedb.kmp.ui.theme.localTypography

@Composable
fun PreferenceSwitchRow(
    modifier: Modifier = Modifier,
    isSelected:Boolean,
    onCheckedChange:(Boolean)->Unit = {},
    text:String
){
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            style = MaterialTheme.localTypography.h2,
            color = MaterialTheme.localColors.onSurface
        )

        Spacer(modifier = Modifier.weight(1f))

        Switch(
            checked = isSelected,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.localColors.primary,
                uncheckedTrackColor = MaterialTheme.localColors.shimmer,
            )
        )
    }
}


@Preview
@Composable
private fun PreferenceSwitchRowPreview(){
    MovieDbTheme {
        PreferenceSwitchRow(
            text = "Switch preference",
            isSelected = true
        )
    }
}