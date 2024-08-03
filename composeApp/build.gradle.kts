import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.jetbrains.kotlin.serialization)
    alias(libs.plugins.compose.compiler)
    id("kotlin-parcelize")
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    jvm("desktop")

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "Shared"
            isStatic = true

            export(projects.core.network)
            export(projects.core.database)
            export(projects.core.data)
            export(projects.core.common)
            export(projects.core.ui)
            export(projects.core.domain)
        }
    }
    
    sourceSets {
        val desktopMain by getting
        val commonMain by getting
        val iosX64Main by getting
        
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            // Koin
            implementation(libs.koin.android)
            implementation(libs.koin.androidx.compose)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            //Koin
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)

            //Serialization
            implementation(libs.kotlinx.serialization.json)

            //Modules
            api(projects.core.network)
            api(projects.core.database)
            api(projects.core.data)
            api(projects.core.common)
            api(projects.core.ui)
            api(projects.core.domain)
        }
        desktopMain.dependencies {
            implementation(libs.jvm.navigation)
            implementation(compose.desktop.currentOs)

            //Modules
            implementation(projects.feature.desktop.home)
            implementation(projects.feature.desktop.lists)
            implementation(projects.feature.desktop.explore)
            implementation(projects.feature.desktop.account)
        }
        commonTest.dependencies {
            //Koin
            implementation(libs.koin.test)
        }
    }

    task("testClasses")
}

android {
    namespace = "org.moviedb.kmp"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "org.moviedb.kmp"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        resourceConfigurations.plus(listOf("en", "uk"))
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }

    androidResources {
        generateLocaleConfig = true
    }

    bundle {
        language {
            enableSplit = false
        }
    }

    dependencies {
        //Navigation
        implementation(libs.androidx.navigation.compose)

        //AppCompat
        implementation(libs.androidx.appcompat)
        implementation(libs.androidx.appcompat.resources)

        debugImplementation(compose.uiTooling)
        implementation(projects.feature.android.home)
        implementation(projects.feature.android.lists)
        implementation(projects.feature.android.explore)
        implementation(projects.feature.android.account)
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "org.moviedb.kmp"
            packageVersion = "1.0.0"
        }
    }
}
