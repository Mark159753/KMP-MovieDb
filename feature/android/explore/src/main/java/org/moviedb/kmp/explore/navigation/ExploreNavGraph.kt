package org.moviedb.kmp.explore.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import org.moviedb.kmp.ui.navigation.Routes

fun NavGraphBuilder.exploreNavGraph(
    contentPaddingValues: PaddingValues,
){
    navigation<Routes.NestedExploreFilterNavigation>(
        startDestination = Routes.BottomNavigation.Explore,
    ){
        exploreScreen(
            contentPadding = contentPaddingValues,
        )

        filterScreen()
    }
}