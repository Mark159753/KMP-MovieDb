package org.moviedb.kmp.ui

import androidx.lifecycle.ViewModel
import org.moviedb.kmp.common.preferences.AppPreferences

class MainViewModel(
    themePreferences: AppPreferences
):ViewModel() {

    val appState = MovieDBAppState(
        themePreferences = themePreferences
    )
}