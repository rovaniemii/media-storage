package com.hy0417sage.mediastorage.search.viewdata

import kotlinx.coroutines.flow.MutableStateFlow

data class SearchResultViewData(
    val imageUrl: String,
    val createDate: String,
    val isBookMark: MutableStateFlow<Boolean>,
)