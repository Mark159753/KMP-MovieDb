import java.util.Properties

plugins {
    id("org.moviedb.kmp.shared")
    kotlin(libs.plugins.kotlinSerialization.get().pluginId).version(libs.versions.kotlin)
    //Plugin for generate BuildConfig
    alias(libs.plugins.buildConfig)
}

buildConfig{
    val localProps =
        Properties().also {
            if (file(rootProject.file("local.properties").path).exists()) {
                it.load(file(rootProject.file("local.properties").path).inputStream())
            }
        }

    buildConfigField("String", "API_TOKEN", localProps.getProperty("API_TOKEN"))
    buildConfigField("String", "BASE_URL", localProps.getProperty("BASE_URL"))
}

kotlin {
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "network"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            //Ktor
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.ktor.client.auth)
            implementation(libs.ktor.client.cio)
            implementation(libs.ktor.client.logging)

            implementation(projects.core.common)
        }

        androidMain.dependencies {
            //Ktor-okhttp
            implementation(libs.ktor.client.okhttp)
            implementation(libs.logging.interceptor)
        }

        iosMain.dependencies {
            //Ktor
            implementation(libs.ktor.client.darwin)
        }

        jvmMain.dependencies {
            //Ktor-okhttp
            implementation(libs.ktor.client.okhttp)
            implementation(libs.logging.interceptor)
        }
    }
}
