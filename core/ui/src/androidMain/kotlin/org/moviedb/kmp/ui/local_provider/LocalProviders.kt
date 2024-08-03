package org.moviedb.kmp.ui.local_provider

import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavController
import androidx.navigation.NavHostController

val LocalNavController = compositionLocalOf<NavHostController> { error("No NavController found!") }