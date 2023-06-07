package com.hy0417sage.mediastorage.di

import com.hy0417sage.data.api.KakaoAPI
import com.hy0417sage.data.api.KakaoClientImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class ApiModule {

    @Provides
    fun provideApi(): KakaoAPI {
        return KakaoClientImpl.create()
    }
}