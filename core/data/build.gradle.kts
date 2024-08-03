plugins {
    id("org.moviedb.kmp.shared")
}

kotlin {

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "data"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            //Room
            implementation(libs.androidx.room.runtime)

            //Paging
            implementation(libs.paging.common)

            implementation(projects.core.database)
            implementation(projects.core.network)
            implementation(projects.core.common)
        }
    }
}
