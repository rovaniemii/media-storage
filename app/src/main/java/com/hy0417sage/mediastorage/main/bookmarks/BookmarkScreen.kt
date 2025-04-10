package com.hy0417sage.mediastorage.main.bookmarks

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.hy0417sage.core.model.SearchItem
import com.hy0417sage.core.ui.DisableOverScroll
import com.hy0417sage.mediastorage.main.view.ListItemView

@Composable
fun BookmarkScreen(
    modifier: Modifier = Modifier,
) {
    val items by remember { mutableStateOf(emptyList<SearchItem>()) }

    DisableOverScroll {
        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
        ) {
            items(
                count = items.size,
                key = { index ->
                    items[index].hashCode()
                },
            ) { index ->
                val item = items[index]

                ListItemView(
                    viewData = item,
                )
            }
        }
    }
}