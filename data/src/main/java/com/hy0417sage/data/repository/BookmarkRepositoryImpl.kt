package com.hy0417sage.data.repository

import com.hy0417sage.core.model.SearchItem
import com.hy0417sage.data.local.dao.BookmarksDao
import com.hy0417sage.data.local.mapper.toBookmarkEntity
import com.hy0417sage.data.local.mapper.toSearchItem
import com.hy0417sage.domain.repository.BookmarksRepository
import javax.inject.Inject

class BookmarkRepositoryImpl @Inject constructor(
    private val bookmarksDao: BookmarksDao,
) : BookmarksRepository {
    override suspend fun addBookmark(item: SearchItem) {
        bookmarksDao.insertBookmark(item.toBookmarkEntity())
    }

    override suspend fun removeBookmark(item: SearchItem) {
        bookmarksDao.deleteBookmark(item.toBookmarkEntity())
    }

    override suspend fun getBookmarks(): List<SearchItem> {
        return bookmarksDao.getAllBookmarks().map { it.toSearchItem() }
    }

    override suspend fun isBookmarked(url: String): Boolean {
        return bookmarksDao.isBookmarked(url)
    }
}