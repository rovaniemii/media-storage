package com.hy0417sage.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hy0417sage.data.local.entity.BookmarkEntity

@Dao
interface BookmarksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmark(item: BookmarkEntity)

    @Delete
    suspend fun deleteBookmark(item: BookmarkEntity)

    @Query("SELECT * FROM bookmarks")
    suspend fun getAllBookmarks(): List<BookmarkEntity>

    @Query("SELECT EXISTS(SELECT 1 FROM bookmarks WHERE imageUrl = :url LIMIT 1)")
    suspend fun isBookmarked(url: String): Boolean
}