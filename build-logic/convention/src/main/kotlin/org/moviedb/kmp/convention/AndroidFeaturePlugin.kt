package org.moviedb.kmp.convention

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.moviedb.kmp.convention.extensions.android.androidCompose
import org.moviedb.kmp.convention.extensions.android.commonCompose
import org.moviedb.kmp.convention.extensions.android.configureAndroidFeature
import org.moviedb.kmp.convention.extensions.android.koin
import org.moviedb.kmp.convention.extensions.android.kotlinxSerialization
import org.moviedb.kmp.convention.extensions.android.navigation
import org.moviedb.kmp.convention.extensions.libs

class AndroidFeaturePlugin: Plugin<Project> {

    override fun apply(target: Project) {
        with(target){
            with(pluginManager){
                apply(libs.findPlugin("androidLibrary").get().get().pluginId)
                apply(libs.findPlugin("jetbrains.kotlin.android").get().get().pluginId)
                apply(libs.findPlugin("jetbrainsCompose").get().get().pluginId)
                apply(libs.findPlugin("jetbrains.kotlin.serialization").get().get().pluginId)
                apply(libs.findPlugin("compose.compiler").get().get().pluginId)
                apply("kotlin-parcelize")
            }

            extensions.configure<LibraryExtension>(::configureAndroidFeature)

            dependencies {
                commonCompose(libs)
                androidCompose(libs)
                navigation(libs)
                kotlinxSerialization(libs)
                koin(libs)
            }
        }
    }
}