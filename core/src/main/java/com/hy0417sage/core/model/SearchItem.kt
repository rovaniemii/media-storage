package com.hy0417sage.core.model

data class SearchItem(
    val imageUrl: String,
    val datetime: String,
    val bookmark: Boolean,
    val bookmarkTime: String, //북마크 된 순서
)