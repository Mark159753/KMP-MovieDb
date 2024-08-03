package org.moviedb.kmp.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.moviedb.kmp.home.ui.HomeScreen
import org.moviedb.kmp.ui.navigation.Routes

fun NavGraphBuilder.homeScreen(){
    composable(
        route = Routes.BottomNavigation.Home::class.java.name
    ) {
        HomeScreen()
    }
}