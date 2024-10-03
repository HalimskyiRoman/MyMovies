package com.op.inc.mymovies.navigation

import com.op.inc.mymovies.R
import com.op.inc.mymovies.navigation.BottomBarItem.Recommendations

sealed class BottomBarItem(
    val label: String,
    val icon: Int,
    val iconFilled: Int,
    val destination: String,
) {
    object Recommendations : BottomBarItem (
        label = "",
        icon = R.drawable.ic_launcher_background,
        iconFilled =  R.drawable.ic_launcher_background,
        destination = Screen.RecommendationsScreen.route,
    )
}

fun getBottomBarItems(): List<BottomBarItem> = listOf(
    Recommendations,
)