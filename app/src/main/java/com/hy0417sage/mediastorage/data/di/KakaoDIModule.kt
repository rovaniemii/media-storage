package com.hy0417sage.mediastorage.data.di

import com.hy0417sage.mediastorage.data.api.KakaoAPI
import com.hy0417sage.mediastorage.data.api.impl.KakaoClientImpl
import com.hy0417sage.mediastorage.data.repository.RepositoryImpl
import com.hy0417sage.mediastorage.data.repository.remote.RemoteDataSource
import com.hy0417sage.mediastorage.data.repository.remote.RemoteDataSourceImpl
import com.hy0417sage.mediastorage.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class KakaoDIModule {
    @Provides
    fun provideClient(): KakaoAPI {
        return KakaoClientImpl.create()
    }

    @Singleton
    @Provides
    fun provideDataSource(
        kakaoAPI: KakaoAPI,
    ): RemoteDataSource {
        return RemoteDataSourceImpl(kakaoAPI)
    }

    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: RemoteDataSource,
    ): Repository {
        return RepositoryImpl(remoteDataSource)
    }
}