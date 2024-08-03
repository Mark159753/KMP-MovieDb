package org.moviedb.kmp.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Routes {

    @Serializable
    data object NestedBottomNavigation:Routes
    @Serializable
    data object NestedExploreFilterNavigation:Routes

    @Serializable
    sealed interface BottomNavigation:Routes{
        @Serializable
        data object Home:BottomNavigation
        @Serializable
        data object Lists:BottomNavigation
        @Serializable
        data object Explore:BottomNavigation
        @Serializable
        data object Account:BottomNavigation
    }

    @Serializable
    data object ExploreFilter:Routes

}