package org.moviedb.kmp.ui.di

import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.moviedb.kmp.common.providers.ProvideAppLanguage
import org.moviedb.kmp.ui.providers.ProvideAppLanguageImpl

actual val providersModule: Module = module {
    factoryOf(::ProvideAppLanguageImpl).bind<ProvideAppLanguage>()
}