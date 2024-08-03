package org.moviedb.kmp.convention.extensions.multiplatform

import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.moviedb.kmp.convention.extensions.libs

internal fun Project.configureKotlinMultiplatform(
    extension: KotlinMultiplatformExtension
) = extension.apply {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    jvm()

    //common dependencies
    sourceSets.apply {

        commonMain {
            dependencies {
                implementation(libs.findLibrary("koin.core").get())
            }
        }

        androidMain {
            dependencies {
                implementation(libs.findLibrary("koin.android").get() )
            }
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
            implementation(libs.findLibrary("koin.test").get())
        }
    }

    task("testClasses")
}