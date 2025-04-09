package com.hy0417sage.data.di.remote

import com.hy0417sage.data.repository.SearchRepositoryImpl
import com.hy0417sage.data.repository.datasource.SearchDataSource
import com.hy0417sage.domain.repository.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(
        searchDataSource: SearchDataSource,
    ): SearchRepository {
        return SearchRepositoryImpl(searchDataSource)
    }
}