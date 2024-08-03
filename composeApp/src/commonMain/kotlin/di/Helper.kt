package di

import org.koin.core.context.startKoin
import org.moviedb.kmp.common.di.coroutinesScopeModule
import org.moviedb.kmp.data.di.pagingModule
import org.moviedb.kmp.data.di.repositoryModule
import org.moviedb.kmp.database.di.dataStoreModule
import org.moviedb.kmp.database.di.databaseModule
import org.moviedb.kmp.domain.di.useCaseModule
import org.moviedb.kmp.network.di.networkModule
import org.moviedb.kmp.ui.di.providersModule

fun initKoin(){
    startKoin {
        modules(appModule())
    }
}

fun appModule() = listOf(
    databaseModule,
    networkModule,
    pagingModule,
    repositoryModule,
    dataStoreModule,
    providersModule,
    coroutinesScopeModule,
    useCaseModule,
    viewModelsModule()
)