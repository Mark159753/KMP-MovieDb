package org.moviedb.kmp.ui.providers

import androidx.appcompat.app.AppCompatDelegate
import org.moviedb.kmp.common.constants.AppLanguage
import org.moviedb.kmp.common.providers.ProvideAppLanguage

class ProvideAppLanguageImpl: ProvideAppLanguage {

    override fun getAppLanguage() = try {
        AppLanguage.valueOf(AppCompatDelegate.getApplicationLocales()[0]!!.language.uppercase())
    }catch (e:Exception){
        AppLanguage.EN
    }
}