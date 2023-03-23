package com.hy0417sage.mediastorage.domain.model

data class ViewDataList(
    val documents: List<ViewData>,
)

data class ViewData(
    val thumbnail: String,
    val datetime: String,
    val source: String,
    val like: Int,
)