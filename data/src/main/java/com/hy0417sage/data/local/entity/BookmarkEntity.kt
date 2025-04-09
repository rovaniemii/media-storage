package com.hy0417sage.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmarks")
data class BookmarkEntity(
    @PrimaryKey val imageUrl: String,
    val dateTime: String,
    val isBookmarked: Boolean,
)