buildscript {
    repositories {
        gradlePluginPortal()
    }
    dependencies {
        classpath (libs.moko.classpath)
    }
}

plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.jetbrainsCompose) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.buildConfig) apply false
    alias(libs.plugins.moko.multiplatform) apply false
    kotlin(libs.plugins.kotlinSerialization.get().pluginId).version(libs.versions.kotlin) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
}