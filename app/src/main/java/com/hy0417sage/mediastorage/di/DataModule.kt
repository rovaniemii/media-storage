package com.hy0417sage.mediastorage.di

import com.hy0417sage.data.remote.service.SearchService
import com.hy0417sage.data.repository.datasource.RemoteDataSource
import com.hy0417sage.data.repository.datasource.RemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataModule {

    @Singleton
    @Provides
    fun provideImageDataSource(
        searchService: SearchService,
    ): RemoteDataSource {
        return RemoteDataSourceImpl(searchService)
    }
}