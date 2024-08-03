plugins {
    id("org.moviedb.kmp.androidFeature")
}


dependencies {
    implementation(libs.paging.compose.common)
    implementation(projects.core.data)
    implementation(projects.core.domain)
    implementation(projects.core.common)
    implementation(projects.core.ui)
}