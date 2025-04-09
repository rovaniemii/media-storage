package com.hy0417sage.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hy0417sage.data.local.dao.BookmarksDao
import com.hy0417sage.data.local.entity.BookmarkEntity

@Database(
    entities = [BookmarkEntity::class],
    version = 1,
    exportSchema = false
)
abstract class BookmarksDatabase : RoomDatabase() {
    abstract fun bookmarksDao(): BookmarksDao
}