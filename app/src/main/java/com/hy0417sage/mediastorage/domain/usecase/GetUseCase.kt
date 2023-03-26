package com.hy0417sage.mediastorage.domain.usecase

import com.hy0417sage.mediastorage.domain.model.ViewData
import com.hy0417sage.mediastorage.domain.repository.Repository
import javax.inject.Inject

class GetUseCase @Inject constructor(
    private val repository: Repository,
) {
    suspend fun getSearchData(
        query: String,
        page: Int,
        size: Int,
    ): List<ViewData> = repository.getSearchData(query, page, size)
}