package org.moviedb.kmp.account.ui

import android.app.LocaleManager
import android.content.Context
import android.os.Build
import android.os.LocaleList
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.ui.text.intl.Locale
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import org.moviedb.kmp.common.constants.AppLanguage
import org.moviedb.kmp.common.preferences.AppPreferences

class AccountViewModel(
    private val appPreferences: AppPreferences
):ViewModel() {

    val isDarkTheme = appPreferences.isDarkMode.filterNotNull()

    val currentLng = appPreferences.currentLocale

    fun onIsDarkModelToggle(isInDark:Boolean){
        viewModelScope.launch {
            appPreferences.changeDarkMode(isInDark)
        }
    }

    fun onChangeLanguage(lng:AppLanguage, context: Context){
        val tage = Locale(lng.name.lowercase()).toLanguageTag()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.getSystemService(LocaleManager::class.java).applicationLocales =
                LocaleList.forLanguageTags(tage)
        } else {
            AppCompatDelegate.setApplicationLocales(
                LocaleListCompat.forLanguageTags(tage)
            )
        }
        viewModelScope.launch {
            appPreferences.setLocale(lng)
        }
    }


}