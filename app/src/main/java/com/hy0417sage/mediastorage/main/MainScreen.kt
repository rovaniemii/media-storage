package com.hy0417sage.mediastorage.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.hy0417sage.mediastorage.main.bookmarks.BookmarkScreen
import com.hy0417sage.mediastorage.main.model.ScreenType
import com.hy0417sage.mediastorage.main.search.SearchScreen
import com.hy0417sage.mediastorage.main.view.BottomNavigationBar

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
) {
    val density = LocalDensity.current

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    var navigationHeight by remember { mutableStateOf(0.dp) }

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = ScreenType.SEARCH,
    ) {
        composable(
            route = ScreenType.SEARCH,
        ) {
            SearchScreen()
        }

        composable(
            route = ScreenType.BOOKMARKS,
        ) {
            BookmarkScreen()
        }
    }

    BottomNavigationBar(
        modifier = modifier
            .onSizeChanged {
                navigationHeight = with(density) { it.height.toDp() }
            },
        currentRoute = currentRoute,
        onClick = { screenRoute ->
            navController.navigate(screenRoute) {
                launchSingleTop = true
                restoreState = true
            }
        }
    )
}