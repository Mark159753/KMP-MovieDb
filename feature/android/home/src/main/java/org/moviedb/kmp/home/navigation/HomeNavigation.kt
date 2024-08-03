package org.moviedb.kmp.home.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.moviedb.kmp.home.ui.HomeScreen
import org.moviedb.kmp.ui.navigation.Routes

fun NavGraphBuilder.homeScreen(
    contentPadding: PaddingValues = PaddingValues()
){
    composable<Routes.BottomNavigation.Home>() {
        HomeScreen(
            contentPadding = contentPadding
        )
    }
}