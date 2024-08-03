package org.moviedb.kmp.ui.extension

import org.moviedb.kmp.common.constants.AppLanguage
import org.moviedb.kmp.ui.MR

fun AppLanguage.displayName() = when(this){
    AppLanguage.EN -> MR.strings.language_en
    AppLanguage.UK -> MR.strings.language_uk
}