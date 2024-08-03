package org.moviedb.kmp.convention.extensions.android

import org.gradle.api.artifacts.VersionCatalog
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.moviedb.kmp.convention.extensions.libs

fun DependencyHandlerScope.androidCompose(libs:VersionCatalog){
    add("implementation", libs.findLibrary("androidx.core.ktx").get())
    add("implementation", libs.findLibrary("androidx.appcompat").get())
    add("implementation", libs.findLibrary("androidx.material").get())
    add("implementation", libs.findLibrary("androidx.activity.compose").get())
    add("implementation", libs.findLibrary("lifecycle.viewmodel").get())
    add("implementation", libs.findLibrary("lifecycle.runtime.ktx").get())
    add("implementation", libs.findLibrary("lifecycle.runtime.compose").get())
    add("implementation", libs.findLibrary("ui.tooling.preview").get())
    add("implementation", libs.findLibrary("material3").get())
    add("implementation", libs.findLibrary("material2").get())

    //Tests
    add("testImplementation", libs.findLibrary("junit").get())
    add("androidTestImplementation", libs.findLibrary("androidx.test.junit").get())
    add("androidTestImplementation", libs.findLibrary("androidx.espresso.core").get())
    add("androidTestImplementation", libs.findLibrary("ui.test.junit4").get())
    add("debugImplementation", libs.findLibrary("ui.test.manifest").get())
    add("debugImplementation", libs.findLibrary("ui.tooling").get())
}

fun DependencyHandlerScope.navigation(libs:VersionCatalog){
    add("implementation", libs.findLibrary("androidx.navigation.compose").get())
}

fun DependencyHandlerScope.commonCompose(libs:VersionCatalog){
    add("implementation", platform(libs.findLibrary("compose.bom").get()))
    add("implementation", libs.findLibrary("compose.ui").get())
    add("implementation", libs.findLibrary("compose.ui.graphics").get())
}

fun DependencyHandlerScope.kotlinxSerialization(libs:VersionCatalog){
    add("implementation", libs.findLibrary("kotlinx.serialization.json").get())
}

fun DependencyHandlerScope.koin(libs:VersionCatalog){
    add("implementation",libs.findLibrary("koin.core").get())
    add("implementation",libs.findLibrary("koin.android").get())
    add("implementation",libs.findLibrary("koin.androidx.compose").get())
    add("implementation",libs.findLibrary("koin.compose.viewmodel").get())
    add("testImplementation",libs.findLibrary("koin.test").get())
}