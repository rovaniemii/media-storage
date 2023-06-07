package com.hy0417sage.mediastorage.di

import com.hy0417sage.data.api.KakaoAPI
import com.hy0417sage.data.repository.remote.ImageRemoteDataSource
import com.hy0417sage.data.repository.remote.ImageRemoteDataSourceImpl
import com.hy0417sage.data.repository.remote.VClipRemoteDataSource
import com.hy0417sage.data.repository.remote.VClipRemoteDataSourceImpl
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
}