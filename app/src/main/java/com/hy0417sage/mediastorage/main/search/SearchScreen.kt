package com.hy0417sage.mediastorage.main.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.hy0417sage.core.model.SearchItem
import com.hy0417sage.core.ui.DisableOverScroll
import com.hy0417sage.mediastorage.R
import com.hy0417sage.mediastorage.main.search.view.SearchWordInputView
import com.hy0417sage.mediastorage.main.view.ListItemView
import com.hy0417sage.mediastorage.main.view.LottieAnimationView
import java.net.UnknownHostException

@Composable
internal fun SearchScreen(
    modifier: Modifier = Modifier,
    navigationHeight: Dp,
    firstItems: LazyPagingItems<SearchItem>,
    fullItems: LazyPagingItems<SearchItem>,
    updateSearchQuery: (changedValue: String) -> Unit,
    onSearchButtonClick: () -> Unit,
) {
    var searchQuery by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize(),
    ) {
        SearchWordInputView(
            modifier = Modifier
                .fillMaxWidth(),
            initValue = searchQuery,
            onValueChange = {
                searchQuery = it
                updateSearchQuery(it)
            },
            onSearchButtonClick = {
                onSearchButtonClick()
            },
        )

        when (val loadState = firstItems.loadState.refresh) {
            is LoadState.NotLoading -> {
                DisableOverScroll {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentPadding = PaddingValues(
                            bottom = navigationHeight,
                        ),
                    ) {
                        items(
                            count = firstItems.itemCount,
                            key = { index ->
                                firstItems[index]?.hashCode() ?: index
                            },
                        ) { index ->
                            firstItems[index]?.let { item ->
                                ListItemView(viewData = item)
                            }
                        }

                        items(
                            count = fullItems.itemCount,
                            key = { index ->
                                fullItems[index]?.hashCode() ?: index
                            },
                        ) { index ->
                            fullItems[index]?.let { item ->
                                ListItemView(viewData = item)
                            }
                        }
                    }
                }
            }

            is LoadState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    LottieAnimationView(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(
                                size = 60.dp,
                            ),
                        jsonString = "loading.json",
                    )
                }
            }

            is LoadState.Error -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                ) {
                    val errorMessage = when (val error = (loadState as? LoadState.Error)?.error) {
                        is UnknownHostException -> stringResource(R.string.search_paging_network_message)
                        is IndexOutOfBoundsException -> stringResource(R.string.search_paging_no_search_message)
                        else -> error?.localizedMessage ?: stringResource(R.string.search_paging_unknown_error_message)
                    }

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Center),
                        text = errorMessage,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}