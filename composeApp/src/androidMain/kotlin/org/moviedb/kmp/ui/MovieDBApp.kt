package org.moviedb.kmp.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import org.moviedb.kmp.navigation.MovieDbNavHost
import org.moviedb.kmp.ui.components.bottom_navigation.MovieDbBottomNavigationBar
import org.moviedb.kmp.ui.components.bottom_navigation.rememberMovieDbBottomNavState
import org.moviedb.kmp.ui.local_provider.LocalNavController
import org.moviedb.kmp.ui.theme.MovieDbTheme

@Composable
internal fun MovieDBApp(
    appState:MovieDBAppState
){

    val isSystemInDark by appState.isSystemInDark.collectAsStateWithLifecycle(initialValue = null)
    val isDark = isSystemInDark ?: isSystemInDarkTheme()

    MovieDbTheme(
        isDarkTheme = isDark
    ) {
        val navController = rememberNavController()
        val bottomBarState = rememberMovieDbBottomNavState(navController)

        CompositionLocalProvider(LocalNavController provides navController) {
            Scaffold(
                contentWindowInsets = WindowInsets(0, 0, 0, 0),
                bottomBar = {
                    AnimatedVisibility(
                        visible = bottomBarState.isVisible,
                        enter = slideInVertically(
                            animationSpec = tween(
                                durationMillis = 200,
                            ),
                            initialOffsetY = { it }
                        ),
                        exit = slideOutVertically(
                            animationSpec = tween(
                                durationMillis = 200,
                            ),
                            targetOffsetY = { it }
                        )
                    ) {
                        MovieDbBottomNavigationBar(
                            state = bottomBarState
                        )
                    }
                }
            ) { contentPadding ->

                MovieDbNavHost(
                    modifier = Modifier
                        .consumeWindowInsets(contentPadding),
                    navController = navController,
                    contentPadding = contentPadding
                )
            }
        }
    }
}