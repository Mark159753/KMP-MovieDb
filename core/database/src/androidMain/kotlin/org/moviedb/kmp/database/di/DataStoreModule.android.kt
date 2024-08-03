package org.moviedb.kmp.database.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import okio.Path.Companion.toPath
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import org.moviedb.kmp.common.constants.SETTINGS_PREFERENCES
import java.io.File

actual fun provideDataStoreModule() = module {
    single<DataStore<Preferences>> {
        PreferenceDataStoreFactory.createWithPath(
            produceFile = { File(androidApplication().filesDir, "datastore/$SETTINGS_PREFERENCES").path.toPath() }
        )
    }
}