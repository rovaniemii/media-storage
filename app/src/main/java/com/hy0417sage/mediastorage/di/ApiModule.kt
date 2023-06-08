package com.hy0417sage.mediastorage.di

import com.hy0417sage.data.remote.service.SearchService
import com.hy0417sage.data.remote.service.SearchClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class ApiModule {

    @Provides
    fun provideApi(): SearchService {
        return SearchClient.create()
    }
}