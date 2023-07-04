package com.hy0417sage.mediastorage.di

import com.hy0417sage.data.remote.model.ApiMapper
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
object DataSourceModule {

    @Singleton
    @Provides
    fun provideDataSource(
        searchService: SearchService,
        apiMapper: ApiMapper,
    ): RemoteDataSource {
        return RemoteDataSourceImpl(searchService, apiMapper)
    }
}