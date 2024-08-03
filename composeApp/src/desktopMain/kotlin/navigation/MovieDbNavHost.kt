package navigation

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
    startDestination: Routes = Routes.NestedBottomNavigation,
){
    NavHost(
        navController = navController,
        startDestination = startDestination::class.java.name,
        modifier = modifier
    ){
        bottomBarNestedGraph(navController)
    }
}