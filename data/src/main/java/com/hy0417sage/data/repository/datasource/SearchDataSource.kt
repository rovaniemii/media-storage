package com.hy0417sage.data.repository.datasource

import androidx.paging.PagingData
import com.hy0417sage.core.model.SearchItem
import kotlinx.coroutines.flow.Flow

/**
 * Api 호출을 통해 Data 를 가져오기 위한 interface
 * Image, VClip - DataSourceImpl 에서 구현된다.
 */

interface SearchDataSource {
    suspend fun firstSearchImages(query: String): Flow<PagingData<SearchItem>>
    suspend fun searchImages(query: String): Flow<PagingData<SearchItem>>
}