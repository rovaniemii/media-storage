package com.hy0417sage.mediastorage.domain.repository

import com.hy0417sage.mediastorage.domain.model.ViewData
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getSearchData(query: String): Flow<List<ViewData>>
}