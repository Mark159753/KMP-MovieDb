package org.moviedb.kmp.common.preferences

import kotlinx.coroutines.flow.Flow
import org.moviedb.kmp.common.constants.AppLanguage

interface AppPreferences {

    val isDarkMode:Flow<Boolean?>

    val currentLocale:Flow<AppLanguage>

    suspend fun changeDarkMode(isEnabled: Boolean?)

    suspend fun setLocale(lng:AppLanguage)


}