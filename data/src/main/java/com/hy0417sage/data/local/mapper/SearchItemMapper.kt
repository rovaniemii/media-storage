package com.hy0417sage.data.local.mapper

import com.hy0417sage.core.model.SearchItem
import com.hy0417sage.data.local.entity.BookmarkEntity

fun BookmarkEntity.toSearchItem(): SearchItem {
    return SearchItem(
        imageUrl = imageUrl,
        datetime = dateTime,
        bookmark = isBookmarked,
    )
}