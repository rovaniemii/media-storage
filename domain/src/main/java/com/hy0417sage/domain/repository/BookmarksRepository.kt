package com.hy0417sage.domain.repository

import com.hy0417sage.core.model.SearchItem

interface BookmarksRepository {
    suspend fun addBookmark(item: SearchItem)
    suspend fun removeBookmark(item: SearchItem)
    suspend fun getBookmarks(): List<SearchItem>
    suspend fun isBookmarked(url: String): Boolean
}