package org.moviedb.kmp.convention

import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.moviedb.kmp.convention.extensions.libs

class DesktopPlugin: Plugin<Project> {


    override fun apply(target: Project) {
        with(target){
            with(pluginManager){
                apply("java-library")
                apply(libs.findPlugin("jetbrains.kotlin.jvm").get().get().pluginId)
                apply(libs.findPlugin("jetbrainsCompose").get().get().pluginId)
                apply(libs.findPlugin("jetbrains.kotlin.serialization").get().get().pluginId)
                apply(libs.findPlugin("compose.compiler").get().get().pluginId)
            }

            extensions.configure<JavaPluginExtension>{
                sourceCompatibility = JavaVersion.VERSION_17
                targetCompatibility = JavaVersion.VERSION_17
            }


            dependencies {
                add("implementation", libs.findLibrary("koin.core").get())
                add("implementation", libs.findLibrary("koin.compose").get())
                add("implementation", libs.findLibrary("koin.compose.viewmodel").get())
                //Serialization
                add("implementation", libs.findLibrary("kotlinx.serialization.json").get())
                //Navigation
                add("implementation", libs.findLibrary("jvm.navigation").get())
            }
        }
    }


}