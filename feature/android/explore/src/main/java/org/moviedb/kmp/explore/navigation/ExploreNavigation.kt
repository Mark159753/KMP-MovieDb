package org.moviedb.kmp.explore.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.moviedb.kmp.explore.ui.ExploreRoute
import org.moviedb.kmp.explore.ui.ExploreViewModel
import org.moviedb.kmp.ui.exstentions.lifecycleIsResumed
import org.moviedb.kmp.ui.exstentions.sharedViewModel
import org.moviedb.kmp.ui.local_provider.LocalNavController
import org.moviedb.kmp.ui.navigation.Routes

fun NavGraphBuilder.exploreScreen(
    contentPadding: PaddingValues = PaddingValues()
){
    composable<Routes.BottomNavigation.Explore> { entry ->
        val navController = LocalNavController.current
        ExploreRoute(
            contentPadding = contentPadding,
            viewModel = entry.sharedViewModel<ExploreViewModel>(navController),
            onNavToFilter = {
                if (navController.currentBackStackEntry?.lifecycleIsResumed() == true){
                    navController.navigate(Routes.ExploreFilter)
                }
            }
        )
    }
}