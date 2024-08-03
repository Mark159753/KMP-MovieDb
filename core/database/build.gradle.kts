plugins {
    id("org.moviedb.kmp.shared")
    kotlin(libs.plugins.kotlinSerialization.get().pluginId).version(libs.versions.kotlin)
    alias(libs.plugins.room)
}

kotlin {
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "database"
            isStatic = true
        }
    }

    sourceSets.commonMain {
        kotlin.srcDir("build/generated/ksp/metadata")
    }

    sourceSets {

        commonMain.dependencies {
            //Room
            implementation(libs.androidx.room.runtime)
            implementation(libs.sqlite.bundled)
            implementation(libs.sqlite)

            //Serialization
            implementation(libs.kotlinx.serialization.json)

            //DataStore
            implementation(libs.androidx.data.store.core)

            implementation(projects.core.common)
        }
    }
}

room {
    schemaDirectory("$projectDir/schemas")
}

dependencies {
    add("kspCommonMainMetadata", libs.androidx.room.compiler)
}

tasks.withType<org.jetbrains.kotlin.gradle.dsl.KotlinCompile<*>>().configureEach {
    if (name != "kspCommonMainKotlinMetadata" ) {
        dependsOn("kspCommonMainKotlinMetadata")
    }
}
