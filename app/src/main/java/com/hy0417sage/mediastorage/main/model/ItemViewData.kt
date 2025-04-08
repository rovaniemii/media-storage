package com.hy0417sage.mediastorage.main.model

import kotlinx.coroutines.flow.MutableStateFlow

data class ItemViewData(
    val imageUrl: String,
    val createDate: String,
    val isBookMark: MutableStateFlow<Boolean>,
)