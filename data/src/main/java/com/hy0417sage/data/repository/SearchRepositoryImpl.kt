package com.hy0417sage.data.repository

import androidx.paging.PagingData
import com.hy0417sage.core.model.SearchItem
import com.hy0417sage.data.repository.datasource.SearchDataSource
import com.hy0417sage.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Domain Layer 의 Repository Interface 구현부.
 * DataSource 를 인자로 받아 컨트롤 하여 필요한 Data 를 가져옴.
 *
 * @param searchDataSource api 에서 가져오는 Data
 */

class SearchRepositoryImpl @Inject constructor(
    private val searchDataSource: SearchDataSource,
) : SearchRepository {
    override suspend fun firstSearchImages(query: String): Flow<PagingData<SearchItem>> {
        return searchDataSource.firstSearchImages(query)
    }

    override suspend fun searchImages(query: String): Flow<PagingData<SearchItem>> {
        return searchDataSource.searchImages(query)
    }
}