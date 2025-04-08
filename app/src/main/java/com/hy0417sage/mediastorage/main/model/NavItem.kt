package com.hy0417sage.mediastorage.main.model

import androidx.annotation.DrawableRes
import com.hy0417sage.mediastorage.R

sealed class NavItem(
    val title: Int,
    @DrawableRes val icon: Int,
    val screenRoute: String,
) {
    data object Search : NavItem(R.string.nav_search, R.drawable.icon_nav_search_24, ScreenType.SEARCH)
    data object Bookmarks : NavItem(R.string.nav_bookmarks, R.drawable.icon_nav_bookmarks_24, ScreenType.BOOKMARKS)
}