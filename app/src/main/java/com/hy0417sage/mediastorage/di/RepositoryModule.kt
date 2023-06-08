package com.hy0417sage.mediastorage.di

import com.hy0417sage.data.repository.SearchRepositoryImpl
import com.hy0417sage.data.repository.datasource.RemoteDataSource
import com.hy0417sage.domain.repository.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: RemoteDataSource,
    ): SearchRepository {
        return SearchRepositoryImpl(remoteDataSource)
    }
}