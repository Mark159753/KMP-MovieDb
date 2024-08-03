package org.moviedb.kmp.common.di

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.qualifier.named
import org.koin.dsl.module

actual val coroutinesScopeModule = module {
    factory<CoroutineScope>{
        CoroutineScope(SupervisorJob() + Dispatchers.Default)
    }
}