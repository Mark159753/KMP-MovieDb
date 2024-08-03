package di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import org.moviedb.kmp.account.ui.AccountScreenState
import ui.MovieDbState

actual fun viewModelsModule() = module {

    factoryOf(::MovieDbState)

    factoryOf(::AccountScreenState)
}