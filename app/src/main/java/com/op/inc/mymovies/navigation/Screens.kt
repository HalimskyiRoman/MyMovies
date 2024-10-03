package com.op.inc.mymovies.navigation

sealed class Screen(
    val route: String,
) {
    object RecommendationsScreen : Screen("recommendations_screen")
}