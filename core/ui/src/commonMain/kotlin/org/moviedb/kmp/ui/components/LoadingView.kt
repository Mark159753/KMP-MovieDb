package org.moviedb.kmp.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.moviedb.kmp.ui.theme.localColors

@Composable
fun LoadingView(
    modifier: Modifier = Modifier
){
    Box(
        modifier,
        contentAlignment = Alignment.Center
    ){
        CircularProgressIndicator(
            modifier = Modifier.size(64.dp),
            color = MaterialTheme.localColors.primary,
        )
    }
}