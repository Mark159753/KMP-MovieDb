package org.moviedb.kmp.explore.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.koin.compose.koinInject
import org.moviedb.kmp.explore.ui.filter.FilterRoute
import org.moviedb.kmp.ui.navigation.Routes

fun NavGraphBuilder.filterScreen(
    onNavBack:()->Unit,
){
    composable(route = Routes.ExploreFilter::class.java.name) {
        FilterRoute(
            onNavBack = onNavBack,
            state = koinInject()
        )
    }
}