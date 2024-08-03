package org.moviedb.kmp.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import org.moviedb.kmp.account.navigation.accountScreen
import org.moviedb.kmp.explore.navigation.exploreNavGraph
import org.moviedb.kmp.explore.navigation.exploreScreen
import org.moviedb.kmp.home.navigation.homeScreen
import org.moviedb.kmp.lists.navigation.listsScreen
import org.moviedb.kmp.ui.navigation.Routes

internal fun NavGraphBuilder.bottomBarNestedGraph(
    contentPadding: PaddingValues = PaddingValues()
){
    navigation<Routes.NestedBottomNavigation>(
        startDestination = Routes.BottomNavigation.Home,
    ){
        homeScreen(
            contentPadding = contentPadding
        )

        listsScreen()

        exploreNavGraph(
            contentPaddingValues = contentPadding
        )

        accountScreen()
    }
}