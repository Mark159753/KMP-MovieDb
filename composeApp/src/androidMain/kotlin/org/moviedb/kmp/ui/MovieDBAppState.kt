package org.moviedb.kmp.ui

import androidx.compose.runtime.Stable
import org.moviedb.kmp.common.preferences.AppPreferences

@Stable
class MovieDBAppState(
    themePreferences: AppPreferences
) {

    val isSystemInDark = themePreferences.isDarkMode
}