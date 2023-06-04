package com.hy0417sage.mediastorage.di

import com.hy0417sage.data.api.KakaoAPI
import com.hy0417sage.data.api.KakaoClientImpl
import com.hy0417sage.data.repository.RepositoryImpl
import com.hy0417sage.data.repository.remote.ImageRemoteDataSource
import com.hy0417sage.data.repository.remote.ImageRemoteDataSourceImpl
import com.hy0417sage.data.repository.remote.VClipRemoteDataSource
import com.hy0417sage.data.repository.remote.VClipRemoteDataSourceImpl
import com.hy0417sage.domain.repository.Repository
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
    fun provideImageDataSource(
        kakaoAPI: KakaoAPI,
    ): ImageRemoteDataSource {
        return ImageRemoteDataSourceImpl(kakaoAPI)
    }

    @Singleton
    @Provides
    fun provideVClipDataSource(
        kakaoAPI: KakaoAPI,
    ): VClipRemoteDataSource {
        return VClipRemoteDataSourceImpl(kakaoAPI)
    }

    @Singleton
    @Provides
    fun provideRepository(
        imageRemoteDataSource: ImageRemoteDataSource,
        vClipRemoteDataSource: VClipRemoteDataSource,
    ): Repository {
        return RepositoryImpl(imageRemoteDataSource, vClipRemoteDataSource)
    }
}