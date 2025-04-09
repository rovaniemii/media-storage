package com.hy0417sage.data.di.remote

import com.hy0417sage.data.remote.service.SearchClient
import com.hy0417sage.data.remote.service.SearchService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object ServiceModule {

    @Provides
    fun provideService(): SearchService {
        return SearchClient.create()
    }
}