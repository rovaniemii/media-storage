package com.hy0417sage.data.di.remote

import com.hy0417sage.data.remote.mapper.ApiMapper
import com.hy0417sage.data.remote.service.SearchService
import com.hy0417sage.data.repository.datasource.SearchDataSource
import com.hy0417sage.data.repository.datasource.SearchDataSourceImpl
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
    ): SearchDataSource {
        return SearchDataSourceImpl(searchService, apiMapper)
    }
}