plugins {
    id("org.moviedb.kmp.desktopFeature")
}

dependencies{
    implementation(compose.runtime)
    implementation(compose.foundation)
    implementation(compose.material)
    implementation(compose.ui)
    implementation(compose.components.resources)
    implementation(compose.components.uiToolingPreview)

    //Paging
    implementation(libs.paging.compose.common)

    //Modules
    api(projects.core.network)
    api(projects.core.database)
    api(projects.core.data)
    api(projects.core.common)
    api(projects.core.ui)
    api(projects.core.domain)
}