package org.moviedb.kmp.ui.components.side_navigation

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

@Composable
fun rememberMovieDbSideNavState(
    navController: NavHostController = rememberNavController(),
): MovieDbSideNavBarState {
    return remember(
        navController
    ) {
        MovieDbSideNavBarState(
            navController,
        )
    }
}

@Stable
class MovieDbSideNavBarState(
    private val navController: NavHostController
) {

    var isExpanded:Boolean by mutableStateOf(false)
        private set

    val currentDestination: String?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination?.route

    private var lastDestination:BottomBarDestinations? = null

    val currentSideBarDestination: BottomBarDestinations?
        @Composable get() = when (currentDestination) {
            Routes.BottomNavigation.Account::class.java.name -> {
                lastDestination = BottomBarDestinations.ACCOUNT
                lastDestination
            }
            Routes.BottomNavigation.Explore::class.java.name  -> {
                lastDestination = BottomBarDestinations.EXPLORE
                lastDestination
            }
            Routes.BottomNavigation.Home::class.java.name  -> {
                lastDestination = BottomBarDestinations.HOME
                lastDestination
            }
            Routes.BottomNavigation.Lists::class.java.name -> {
                lastDestination = BottomBarDestinations.LISTS
                lastDestination
            }
            else -> lastDestination
        }

    val sideBarDestinations: List<BottomBarDestinations> = BottomBarDestinations.entries

    fun onExpandClick(){
        isExpanded = isExpanded.not()
    }

    fun navigateToSideBarDestination(sideBarDestination: BottomBarDestinations) {
        String::class.simpleName
        val navOps = navOptions {

            popUpTo(navController.graph.findStartDestination().route!!) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when(sideBarDestination){
            BottomBarDestinations.HOME -> navController.navigate(Routes.BottomNavigation.Home::class.java.name, navOps)
            BottomBarDestinations.LISTS -> navController.navigate(Routes.BottomNavigation.Lists::class.java.name, navOps)
            BottomBarDestinations.EXPLORE -> navController.navigate(Routes.BottomNavigation.Explore::class.java.name, navOps)
            BottomBarDestinations.ACCOUNT -> navController.navigate(Routes.BottomNavigation.Account::class.java.name, navOps)
        }
    }
}