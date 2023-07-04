package com.hy0417sage.domain.repository

import androidx.paging.PagingData
import com.hy0417sage.core.model.SearchItem
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun firstSearchImages(query: String): Flow<PagingData<SearchItem>>
    suspend fun searchImages(query: String): Flow<PagingData<SearchItem>>
}