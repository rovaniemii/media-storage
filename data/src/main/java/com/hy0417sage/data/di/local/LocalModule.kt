package com.hy0417sage.data.di.local

import android.content.Context
import androidx.room.Room
import com.hy0417sage.data.local.BookmarksDatabase
import com.hy0417sage.data.local.dao.BookmarksDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideBookmarksDatabase(@ApplicationContext context: Context): BookmarksDatabase {
        return Room.databaseBuilder(
            context,
            BookmarksDatabase::class.java,
            "bookmarks_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideBookmarksDao(db: BookmarksDatabase): BookmarksDao {
        return db.bookmarksDao()
    }
}