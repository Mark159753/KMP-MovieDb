package org.moviedb.kmp.common.providers

import org.moviedb.kmp.common.constants.AppLanguage

fun interface ProvideAppLanguage{
    fun getAppLanguage():AppLanguage
}