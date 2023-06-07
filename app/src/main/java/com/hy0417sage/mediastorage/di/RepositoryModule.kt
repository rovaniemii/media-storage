package com.hy0417sage.mediastorage.di

import com.hy0417sage.data.repository.RepositoryImpl
import com.hy0417sage.data.repository.remote.ImageRemoteDataSource
import com.hy0417sage.data.repository.remote.VClipRemoteDataSource
import com.hy0417sage.domain.repository.Repository
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
        imageRemoteDataSource: ImageRemoteDataSource,
        vClipRemoteDataSource: VClipRemoteDataSource,
    ): Repository {
        return RepositoryImpl(imageRemoteDataSource, vClipRemoteDataSource)
    }
}