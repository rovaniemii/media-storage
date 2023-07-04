package com.hy0417sage.domain.usecase

import androidx.paging.PagingData
import com.hy0417sage.core.model.SearchItem
import com.hy0417sage.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FirstSearchImagesUseCase @Inject constructor(
    private val repository: SearchRepository
) {
    suspend fun invoke(query: String): Flow<PagingData<SearchItem>> {
        return repository.firstSearchImages(query)
    }
}
