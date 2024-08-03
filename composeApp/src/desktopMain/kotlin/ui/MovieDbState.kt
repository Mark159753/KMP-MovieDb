package ui

import org.moviedb.kmp.common.preferences.AppPreferences

class MovieDbState(
    themePreferences: AppPreferences
) {

    val isSystemInDark = themePreferences.isDarkMode
}