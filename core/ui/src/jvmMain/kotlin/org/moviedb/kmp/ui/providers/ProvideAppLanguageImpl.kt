package org.moviedb.kmp.ui.providers

import org.moviedb.kmp.common.constants.AppLanguage
import org.moviedb.kmp.common.providers.ProvideAppLanguage

class ProvideAppLanguageImpl: ProvideAppLanguage {

    override fun getAppLanguage() = try {
        AppLanguage.valueOf(System.getProperty("user.language").uppercase())
    }catch (e:Exception){
        AppLanguage.EN
    }
}