//package com.op.inc.mymovies.navigation
//
//import android.content.Context
//import android.os.Build
//import android.view.Window
//import android.view.WindowInsets
//import androidx.annotation.RequiresApi
//import androidx.compose.animation.AnimatedVisibility
//import androidx.compose.animation.slideInVertically
//import androidx.compose.animation.slideOutVertically
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.material.BottomNavigation
//import androidx.compose.material.BottomNavigationItem
//import androidx.compose.material3.Scaffold
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableIntStateOf
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavDestination.Companion.hierarchy
//import androidx.navigation.NavGraph.Companion.findStartDestination
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.currentBackStackEntryAsState
//import androidx.navigation.compose.rememberNavController
//import androidx.navigation.navigation
//import com.op.inc.mymovies.presentation.screens.RecommendationsScreen
//
//@RequiresApi(Build.VERSION_CODES.R)
//@Composable
//fun Navigation(
//    context: Context,
//    window: Window,
//    bottomBarItems: List<BottomBarItem> = getBottomBarItems(),
//) {
//
//    var selected by remember {
//        mutableStateOf(0)
//    }
//    var isVisibleBottomBar by remember {
//        mutableStateOf(true)
//    }
//
//    val navController = rememberNavController()
//
//    var requirePaddingForBottomNavBar by remember {
//        mutableIntStateOf(0)
//    }
//    var navigationBarHeight = 0
//
//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//        val windowInsets = window.decorView.rootWindowInsets
//        navigationBarHeight =
//            windowInsets?.getInsets(WindowInsets.Type.navigationBars())?.bottom ?: 0
//    } else {
//        val resourceId =
//            context.resources.getIdentifier("navigation_bar_height", "dimen", "android")
//        navigationBarHeight = if (resourceId > 0) {
//            context.resources.getDimensionPixelSize(resourceId)
//        } else {
//            0
//        }
//    }
//    requirePaddingForBottomNavBar = navigationBarHeight / 2
//    //''
//    Scaffold(
//        modifier = Modifier.padding(
//            bottom = if (isVisibleBottomBar) requirePaddingForBottomNavBar.dp else 0.dp
//        ), bottomBar = {
//            AnimatedVisibility(
//                visible = isVisibleBottomBar,
//                enter = slideInVertically(initialOffsetY = { it }),
//                exit = slideOutVertically(targetOffsetY = { it }),
//                modifier = Modifier.background(Color.White)
//            ) {
//                BottomNavigation(
//                    elevation = 0.dp,
//                    backgroundColor = Color.White,
//                    contentColor = Color.Black,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .background(Color.Black),
//                ) {
//                    val navBackStackEntry by navController.currentBackStackEntryAsState()
//                    val currentDestination = navBackStackEntry?.destination
//                    bottomBarItems.forEachIndexed { index, screen ->
//                        BottomNavigationItem(
//                            icon = {
//                                Row(
//                                    horizontalArrangement = Arrangement.Center,
//                                    verticalAlignment = Alignment.CenterVertically,
//                                    modifier = Modifier.fillMaxWidth()
//                                ) {
//                                    Image(
//                                        painter = painterResource(
//                                            id = if (selected == index) screen.iconFilled
//                                            else screen.icon
//                                        ), contentDescription = "", modifier = Modifier.size(42.dp)
//                                    )
//                                }
//                            },
//                            selected = currentDestination?.hierarchy?.any { it.route == screen.destination } == true,
//                            onClick = {
//                                if (currentDestination?.hierarchy?.any { it.route == Screen.RecommendationsScreen.route } == true) {
//                                    selected = index
//                                    navController.navigate(screen.destination) {
//                                        popUpTo(navController.graph.findStartDestination().id) {
//                                            inclusive = true
//                                        }
//                                    }
//                                }
//                                if (selected != index) {
//                                    selected = index
//                                    navController.navigate(screen.destination) {
//                                        popUpTo(navController.graph.findStartDestination().id) {
//                                            saveState = true
//                                        }
//                                        launchSingleTop = true
//                                        restoreState = true
//                                    }
//                                }
//
//                            },
//                        )
//                    }
//                }
//            }
//        }) { innerPadding ->
//        NavHost(
//            navController, startDestination = "main",
//            Modifier
//                .padding(
//                    PaddingValues(
//                        top = 0.dp, bottom = innerPadding.calculateBottomPadding()
//                    )
//                )
//                .fillMaxSize(1f)
//        ) {
//            navigation(
//                startDestination = Screen.RecommendationsScreen.route, route = "main"
//            ) {
//                composable(route = Screen.RecommendationsScreen.route) { entry ->
//                    selected = 0
//                    RecommendationsScreen()
//                }
//
//            }
//        }
//    }
//}