package navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import org.moviedb.kmp.account.navigation.accountScreen
import org.moviedb.kmp.explore.navigation.exploreNavGraph
import org.moviedb.kmp.explore.navigation.exploreScreen
import org.moviedb.kmp.home.navigation.homeScreen
import org.moviedb.kmp.lists.navigation.listsScreen
import org.moviedb.kmp.ui.navigation.Routes

internal fun NavGraphBuilder.bottomBarNestedGraph(
    navController: NavController
){
    navigation(
        startDestination = Routes.BottomNavigation.Home::class.java.name,
        route = Routes.NestedBottomNavigation::class.java.name
    ){
        homeScreen()

        listsScreen()

        exploreNavGraph(navController = navController)

        accountScreen()
    }
}