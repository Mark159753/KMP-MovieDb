package org.moviedb.kmp.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import org.moviedb.kmp.ui.navigation.Routes

@Composable
fun MovieDbNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination:Routes = Routes.NestedBottomNavigation,
    contentPadding: PaddingValues = PaddingValues()
){
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ){
        bottomBarNestedGraph(
            contentPadding = contentPadding
        )
    }
}