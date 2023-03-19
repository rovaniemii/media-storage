package com.hy0417sage.mediastorage.domain.usecase

import com.hy0417sage.mediastorage.domain.model.ViewData
import com.hy0417sage.mediastorage.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUseCase @Inject constructor(
    private val repository: Repository,
) {
    fun getFlowData(
        query: String,
    ): Flow<List<ViewData>> = repository.getSearchFlow(query)
}