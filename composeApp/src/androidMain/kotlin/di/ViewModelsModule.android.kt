package di

import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import org.moviedb.kmp.account.ui.AccountViewModel
import org.moviedb.kmp.explore.ui.ExploreViewModel
import org.moviedb.kmp.home.ui.HomeViewModel
import org.moviedb.kmp.ui.MainViewModel

actual fun viewModelsModule() = module {
    viewModelOf(::MainViewModel)

    viewModelOf(::HomeViewModel)

    viewModelOf(::AccountViewModel)

    viewModelOf(::ExploreViewModel)
}