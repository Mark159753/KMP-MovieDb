package org.moviedb.kmp.explore.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.koin.compose.koinInject
import org.moviedb.kmp.explore.ui.ExploreRoute
import org.moviedb.kmp.ui.navigation.Routes

fun NavGraphBuilder.exploreScreen(
    onNavToFilter:()->Unit
){
    composable(
        route = Routes.BottomNavigation.Explore::class.java.name
    ) {
        ExploreRoute(
            onNavToFilter = onNavToFilter,
            state = koinInject()
        )
    }
}