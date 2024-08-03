package org.moviedb.kmp.ui.providers

import org.moviedb.kmp.common.constants.AppLanguage
import org.moviedb.kmp.common.providers.ProvideAppLanguage
import platform.Foundation.NSLocale
import platform.Foundation.currentLocale
import platform.Foundation.languageCode

class ProvideAppLanguageImpl:ProvideAppLanguage {

    override fun getAppLanguage() = try {
        AppLanguage.valueOf(NSLocale.currentLocale.languageCode.uppercase())
    }catch (e:Exception){
        AppLanguage.EN
    }
}