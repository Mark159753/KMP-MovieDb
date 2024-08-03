package org.moviedb.kmp.ui.components.bottom_navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import org.moviedb.kmp.ui.navigation.BottomBarDestinations
import org.moviedb.kmp.ui.navigation.Routes
import org.moviedb.kmp.ui.navigation.toBottomRoute


@Composable
fun rememberMovieDbBottomNavState(
    navController: NavHostController = rememberNavController(),
): MovieDbBottomNavState {
    return remember(
        navController
    ) {
        MovieDbBottomNavState(
            navController,
        )
    }
}

@Stable
class MovieDbBottomNavState(
    private val navController: NavHostController
) {

    val currentDestination: Routes?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.toBottomRoute()

    val currentBottomBarDestination: BottomBarDestinations?
        @Composable get() = when (currentDestination) {
            Routes.BottomNavigation.Account -> BottomBarDestinations.ACCOUNT
            Routes.BottomNavigation.Explore  -> BottomBarDestinations.EXPLORE
            Routes.BottomNavigation.Home  -> BottomBarDestinations.HOME
            Routes.BottomNavigation.Lists -> BottomBarDestinations.LISTS
            else -> null
        }

    val isVisible:Boolean
        @Composable get() = currentBottomBarDestination != null

    val bottomBarDestinations: List<BottomBarDestinations> = BottomBarDestinations.entries

    fun navigateToBottomBarDestination(bottomBarDestination: BottomBarDestinations) {
        String::class.simpleName
        val navOps = navOptions {

            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when(bottomBarDestination){
            BottomBarDestinations.HOME -> navController.navigate(Routes.BottomNavigation.Home, navOps)
            BottomBarDestinations.LISTS -> navController.navigate(Routes.BottomNavigation.Lists, navOps)
            BottomBarDestinations.EXPLORE -> navController.navigate(Routes.BottomNavigation.Explore, navOps)
            BottomBarDestinations.ACCOUNT -> navController.navigate(Routes.BottomNavigation.Account, navOps)
        }
    }
}