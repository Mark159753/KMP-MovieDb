package org.moviedb.kmp.explore.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import org.moviedb.kmp.ui.navigation.Routes

fun NavGraphBuilder.exploreNavGraph(
    navController: NavController,
){
    navigation(
        startDestination = Routes.BottomNavigation.Explore::class.java.name,
        route = Routes.NestedExploreFilterNavigation::class.java.name
    ){
        exploreScreen(
            onNavToFilter = {
                navController.navigate(route = Routes.ExploreFilter::class.java.name)
            },
        )

        filterScreen(
            onNavBack = { navController.navigateUp() },
        )
    }
}