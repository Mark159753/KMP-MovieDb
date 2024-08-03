package org.moviedb.kmp.explore.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.moviedb.kmp.explore.ui.ExploreViewModel
import org.moviedb.kmp.explore.ui.filter.FilterRoute
import org.moviedb.kmp.ui.exstentions.sharedViewModel
import org.moviedb.kmp.ui.local_provider.LocalNavController
import org.moviedb.kmp.ui.navigation.Routes

fun NavGraphBuilder.filterScreen(){
    composable<Routes.ExploreFilter> { entry ->
        val navController = LocalNavController.current
        FilterRoute(
            viewModel = entry.sharedViewModel<ExploreViewModel>(navController),
            onNavBack = { navController.navigateUp() }
        )
    }
}