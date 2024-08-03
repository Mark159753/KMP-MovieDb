package org.moviedb.kmp.lists.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.moviedb.kmp.ui.theme.localColors

@Composable
fun ListsScreen(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.localColors.background)
            .statusBarsPadding()
    ){
        Text(text = "ListScreen")
    }
}

@Preview
@Composable
private fun ListsScreenPreview(){

}