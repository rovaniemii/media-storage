package com.hy0417sage.mediastorage.domain.model

data class ViewDataList(
    val documents: List<ViewData>,
)

data class ViewData(
    val thumbnail: String,
    val datetime: String, // 최신부터 나타나도록
)