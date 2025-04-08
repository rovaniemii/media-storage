package com.hy0417sage.mediastorage.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.hy0417sage.core.ui.DisableOverScroll
import com.hy0417sage.mediastorage.search.view.SearchResultComponentView
import com.hy0417sage.mediastorage.search.view.SearchWordInputView
import com.hy0417sage.mediastorage.search.viewdata.SearchResultViewData

@Composable
internal fun SearchScreen(
    modifier: Modifier = Modifier,
) {
    var initValue by remember { mutableStateOf("") }
    val items by remember { mutableStateOf(emptyList<SearchResultViewData>()) }

    Column(
        modifier = modifier
            .fillMaxSize(),
    ) {
        SearchWordInputView(
            modifier = Modifier
                .fillMaxWidth(),
            initValue = initValue,
            onValueChange = { changedValue ->
                initValue = changedValue
            },
        )

        DisableOverScroll {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                items(
                    count = items.size,
                    key = { index ->
                        items[index].hashCode()
                    },
                ) { index ->
                    val item = items[index]

                    SearchResultComponentView(
                        viewData = item,
                    )
                }
            }
        }
    }
}