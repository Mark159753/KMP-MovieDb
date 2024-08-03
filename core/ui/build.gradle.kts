import org.moviedb.kmp.convention.extensions.android.androidCompose
import org.moviedb.kmp.convention.extensions.android.navigation

plugins {
    id("org.moviedb.kmp.shared")
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.jetbrains.kotlin.serialization)
    alias(libs.plugins.moko.multiplatform)
    id("kotlin-parcelize")
}

kotlin {
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "ui"
            isStatic = true

            export(libs.moko.resource)
            export(libs.moko.graphics)
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.uiToolingPreview)
            implementation(project.dependencies.platform(libs.compose.bom))
            //Koin
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)

            //Serialization
            implementation(libs.kotlinx.serialization.json)

            //Coin
            implementation(libs.ktor.client.core)
            api(libs.coil.compose.core)
            api(libs.coil.compose)
            api(libs.coil.mp)
            api(libs.coil.network.ktor)

            //Resources
            api(libs.moko.resource)
            api(libs.moko.resource.compose)

            //Modules
            implementation(projects.core.data)
            implementation(projects.core.common)
            implementation(projects.core.domain)
        }
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)

            // Koin
            implementation(libs.koin.androidx.compose)
        }
        jvmMain.dependencies {
            implementation(libs.jvm.native.parameters)
            implementation(libs.jvm.navigation)
            // Use swing lib to prevent Main.Dispatcher error in Coil Async Image on Desktop
            runtimeOnly(libs.kotlinx.coroutines.swing)
        }
    }

}

android {
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    buildFeatures {
        compose = true
    }

    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

    dependencies {
        androidCompose(libs)
        navigation(libs)
    }
}

multiplatformResources {
    resourcesPackage.set("org.moviedb.kmp.ui")
}
