package com.hy0417sage.mediastorage.data.di

import com.hy0417sage.mediastorage.data.api.KakaoAPI
import com.hy0417sage.mediastorage.data.api.impl.KakaoClientImpl
import com.hy0417sage.mediastorage.data.repository.RepositoryImpl
import com.hy0417sage.mediastorage.data.repository.search.remote.ImageRemoteDataSource
import com.hy0417sage.mediastorage.data.repository.search.remote.ImageRemoteDataSourceImpl
import com.hy0417sage.mediastorage.data.repository.search.remote.VClipRemoteDataSource
import com.hy0417sage.mediastorage.data.repository.search.remote.VClipRemoteDataSourceImpl
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