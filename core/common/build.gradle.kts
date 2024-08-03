plugins {
    id("org.moviedb.kmp.shared")
    alias(libs.plugins.moko.multiplatform)
}

kotlin {
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "Common"
            isStatic = true

            export(libs.moko.resource)
            export(libs.moko.graphics)
        }
    }
    
    sourceSets {
        commonMain.dependencies {
            //Resources
            api(libs.moko.resource)
            api(libs.moko.resource.compose)

            api(libs.kotlinx.collections.immutable)
            api(libs.dateTime)
            api(libs.kotlinx.coroutines.core)
            api(libs.logging.kermit)
        }
    }
}

multiplatformResources {
    resourcesPackage.set("org.moviedb.kmp.common")
    resourcesClassName.set("CommonRes")
}