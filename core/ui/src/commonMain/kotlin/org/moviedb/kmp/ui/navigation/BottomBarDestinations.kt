package org.moviedb.kmp.ui.navigation

import androidx.navigation.NavBackStackEntry
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.StringResource
import org.moviedb.kmp.ui.MR

enum class BottomBarDestinations(
    val icon: ImageResource,
    val titleText: StringResource,
) {

    HOME(
        icon = MR.images.home,
        titleText = MR.strings.bottom_nav_home,
    ),

    LISTS(
        icon = MR.images.list_ic,
        titleText = MR.strings.bottom_nav_lists,
    ),

    EXPLORE(
        icon = MR.images.explore_ic,
        titleText = MR.strings.bottom_nav_explore,
    ),

    ACCOUNT(
        icon = MR.images.account_circle_ic,
        titleText = MR.strings.bottom_nav_account,
    )

}

internal fun NavBackStackEntry?.toBottomRoute(): Routes? {
    return this?.destination?.route?.substringBefore("?")?.substringBefore("/")
        ?.substringAfterLast(".")?.let {
            when (it) {
                Routes.BottomNavigation.Account::class.simpleName -> Routes.BottomNavigation.Account
                Routes.BottomNavigation.Explore::class.simpleName  -> Routes.BottomNavigation.Explore
                Routes.BottomNavigation.Home::class.simpleName  -> Routes.BottomNavigation.Home
                Routes.BottomNavigation.Lists::class.simpleName -> Routes.BottomNavigation.Lists
                else -> null
            }
        }
}