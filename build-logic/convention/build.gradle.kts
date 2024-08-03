import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "org.moviedb.kmp.buildlogic"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("shared"){
            id = "org.moviedb.kmp.shared"
            implementationClass = "org.moviedb.kmp.convention.SharedPlugin"
        }
    }
    plugins {
        register("androidFeature"){
            id = "org.moviedb.kmp.androidFeature"
            implementationClass = "org.moviedb.kmp.convention.AndroidFeaturePlugin"
        }
    }
    plugins {
        register("desktopFeature"){
            id = "org.moviedb.kmp.desktopFeature"
            implementationClass = "org.moviedb.kmp.convention.DesktopPlugin"
        }
    }
}