package com.hy0417sage.mediastorage.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.hy0417sage.mediastorage.main.bookmarks.BookmarkScreen
import com.hy0417sage.mediastorage.main.model.ScreenType
import com.hy0417sage.mediastorage.main.search.SearchScreen
import com.hy0417sage.mediastorage.main.view.BottomNavigationBar

@Composable
fun MainNavScreen(
    modifier: Modifier = Modifier,
    sharedViewModel: SharedViewModel = viewModel(),
) {
    val density = LocalDensity.current
    val navController = rememberNavController()

    val firstItems = sharedViewModel.firstPagingData.collectAsLazyPagingItems()
    val fullItems = sharedViewModel.pagingData.collectAsLazyPagingItems()

    var navigationHeight by remember { mutableStateOf(0.dp) }

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        NavHost(
            modifier = Modifier
                .fillMaxSize(),
            navController = navController,
            startDestination = ScreenType.SEARCH,
        ) {
            composable(
                route = ScreenType.SEARCH,
            ) {
                SearchScreen(
                    navigationHeight = navigationHeight,
                    firstItems = firstItems,
                    fullItems = fullItems,
                    updateSearchQuery = sharedViewModel::updateSearchQuery,
                    onSearchButtonClick = sharedViewModel::onSearchButtonClick,
                )
            }

            composable(
                route = ScreenType.BOOKMARKS,
            ) {
                BookmarkScreen()
            }
        }

        BottomNavigationBar(
            modifier = Modifier
                .align(
                    alignment = Alignment.BottomCenter,
                )
                .onSizeChanged {
                    navigationHeight = with(density) { it.height.toDp() }
                },
            navController = navController,
            onClick = { screenRoute ->
                navController.navigate(screenRoute) {
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
    }
}