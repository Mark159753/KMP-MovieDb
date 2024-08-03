package org.moviedb.kmp.account.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.moviedb.kmp.account.ui.AccountScreen
import org.moviedb.kmp.ui.navigation.Routes

fun NavGraphBuilder.accountScreen(){
    composable(
        route = Routes.BottomNavigation.Account::class.java.name
    ) {
        AccountScreen()
    }
}