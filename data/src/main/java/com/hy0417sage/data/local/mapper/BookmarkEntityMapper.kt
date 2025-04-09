package com.hy0417sage.data.local.mapper

import com.hy0417sage.core.model.SearchItem
import com.hy0417sage.data.local.entity.BookmarkEntity

fun SearchItem.toBookmarkEntity(): BookmarkEntity {
    return BookmarkEntity(
        imageUrl = imageUrl,
        dateTime = datetime,
        isBookmarked = bookmark,
    )
}