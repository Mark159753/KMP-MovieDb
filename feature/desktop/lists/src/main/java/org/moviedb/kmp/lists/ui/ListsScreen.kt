package org.moviedb.kmp.lists.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.moviedb.kmp.ui.theme.localColors

@Composable
fun ListsScreen(){
    Box(
        modifier = Modifier
            .background(MaterialTheme.localColors.background)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){

        Text(
            text = "Lists"
        )
    }
}