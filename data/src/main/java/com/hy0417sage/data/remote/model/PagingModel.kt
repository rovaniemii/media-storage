package com.hy0417sage.data.remote.model

import com.hy0417sage.core.model.SearchItem

data class PagingModel(
    val searchItemList: List<SearchItem>,
    val totalSize: Int,
    val exception: Exception?,
)