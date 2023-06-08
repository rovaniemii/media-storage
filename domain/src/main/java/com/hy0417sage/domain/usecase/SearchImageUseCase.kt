package com.hy0417sage.domain.usecase

import com.hy0417sage.core.model.SearchItem
import com.hy0417sage.domain.repository.SearchRepository
import javax.inject.Inject

class SearchImageUseCase @Inject constructor(
    private val searchRepository: SearchRepository,
) {
    suspend fun invoke(
        query: String,
        page: Int
    ): List<SearchItem> = searchRepository.getSearchImage(query, page)
}