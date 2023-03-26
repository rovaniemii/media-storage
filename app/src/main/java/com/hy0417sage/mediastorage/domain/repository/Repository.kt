package com.hy0417sage.mediastorage.domain.repository

import com.hy0417sage.mediastorage.domain.model.ViewData

interface Repository {
    suspend fun getSearchData(query: String): List<ViewData>
}