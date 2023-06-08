package com.hy0417sage.domain.repository

import com.hy0417sage.core.model.SearchItem

interface SearchRepository {
    suspend fun getSearchImage(
        query: String,
        page: Int,
    ): List<SearchItem>
}