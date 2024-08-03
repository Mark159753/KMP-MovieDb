package org.moviedb.kmp.lists.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.moviedb.kmp.lists.ui.ListsScreen
import org.moviedb.kmp.ui.navigation.Routes

fun NavGraphBuilder.listsScreen(){
    composable<Routes.BottomNavigation.Lists> {
        ListsScreen()
    }
}