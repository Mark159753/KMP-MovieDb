package org.moviedb.kmp.database.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import co.touchlab.kermit.Logger
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.moviedb.kmp.common.constants.AppLanguage
import org.moviedb.kmp.common.preferences.AppPreferences
import org.moviedb.kmp.common.providers.ProvideAppLanguage

class AppPreferencesImpl(
    private val dataStore: DataStore<Preferences>,
    private val appLanguageProvider:ProvideAppLanguage
): AppPreferences {

    private val darkModeKey = booleanPreferencesKey("$PREFS_TAG_KEY$IS_DARK_MODE")
    private val appLocaleKey = stringPreferencesKey("$PREFS_TAG_KEY$APP_LOCALE")

    override val isDarkMode: Flow<Boolean?> = dataStore.data.map { preferences ->
        preferences[darkModeKey]
    }

    override val currentLocale: Flow<AppLanguage> = dataStore.data.map { preferences ->
        preferences[appLocaleKey]?.let { lng ->
            try {
                AppLanguage.valueOf(lng)
            }catch (e:Exception){
                appLanguageProvider.getAppLanguage()
            }
        } ?: appLanguageProvider.getAppLanguage()
    }

    override suspend fun setLocale(lng: AppLanguage) {
        dataStore.edit { preferences ->
            preferences[appLocaleKey] = lng.name
        }
    }

    override suspend fun changeDarkMode(isEnabled: Boolean?) {
        dataStore.edit { preferences ->
            if (isEnabled == null){
                preferences.remove(darkModeKey)
            }else{
                preferences[darkModeKey] = isEnabled
            }
        }
    }

    private companion object {
        private const val PREFS_TAG_KEY = "AppPreferences"
        private const val IS_DARK_MODE = "prefsBoolean"
        private const val APP_LOCALE = "app_locale"
    }
}