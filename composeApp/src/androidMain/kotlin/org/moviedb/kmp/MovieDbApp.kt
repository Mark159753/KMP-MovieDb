package org.moviedb.kmp

import android.app.Application
import di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MovieDbApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MovieDbApp)
            androidLogger()
            modules(appModule())
        }
    }
}