package org.moviedb.kmp.account.ui

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import org.moviedb.kmp.common.constants.AppLanguage
import org.moviedb.kmp.common.preferences.AppPreferences
import java.util.Locale

class AccountScreenState(
    private val appPreferences: AppPreferences,
    private val scope: CoroutineScope,
) {

    val isDarkTheme = appPreferences.isDarkMode.filterNotNull()

    val currentLng = appPreferences.currentLocale

    fun onIsDarkModelToggle(isInDark:Boolean){
        scope.launch {
            appPreferences.changeDarkMode(isInDark)
        }
    }

    fun onChangeLanguage(lng: AppLanguage){
        Locale.setDefault(Locale(lng.name.lowercase()))
        scope.launch {
            appPreferences.setLocale(lng)
        }
    }
}