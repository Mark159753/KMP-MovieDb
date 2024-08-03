package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import navigation.MovieDbNavHost
import org.koin.compose.koinInject
import org.moviedb.kmp.common.preferences.AppPreferences
import org.moviedb.kmp.ui.components.side_navigation.MovieDbSideNavBar
import org.moviedb.kmp.ui.components.side_navigation.rememberMovieDbSideNavState
import org.moviedb.kmp.ui.theme.MovieDbTheme
import org.moviedb.kmp.ui.theme.localColors
import java.util.Locale

@Composable
fun MovieDBApp(
    state: MovieDbState = koinInject()
){
    val isDark by state.isSystemInDark.collectAsState(null)
    val navController = rememberNavController()

    val appPreferences: AppPreferences = koinInject()
    val lng by appPreferences.currentLocale.collectAsState(initial = null)

    LaunchedEffect(lng){
        lng?.name?.lowercase()?.let {
            Locale.setDefault(Locale(it))
        }
    }

    MovieDbTheme(
        isDarkTheme = isDark ?: isSystemInDarkTheme()
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
        ) {
            MovieDbSideNavBar(
                state = rememberMovieDbSideNavState(navController)
            )

            Column(
                modifier = Modifier
                    .background(MaterialTheme.localColors.background)
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                MovieDbNavHost(
                    modifier = Modifier
                        .fillMaxSize(),
                    navController = navController
                )
            }
        }
    }
}