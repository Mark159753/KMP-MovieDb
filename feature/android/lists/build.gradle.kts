plugins {
    id("org.moviedb.kmp.androidFeature")
}


dependencies {
    implementation(projects.core.data)
    implementation(projects.core.common)
    implementation(projects.core.ui)
}