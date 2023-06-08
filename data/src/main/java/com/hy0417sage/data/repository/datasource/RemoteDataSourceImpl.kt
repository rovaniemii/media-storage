package com.hy0417sage.data.repository.datasource

import com.hy0417sage.data.remote.service.SearchService
import com.hy0417sage.data.remote.model.ResultSearchImage
import com.hy0417sage.data.remote.model.ResultSearchVClip
import javax.inject.Inject

/**
 * Image - DataSource 에서 선언한 Interface 의 구현부.
 * 해당 Interface 를 상속받아 사용한다.
 */

class RemoteDataSourceImpl @Inject constructor(
    private val searchService: SearchService,
) : RemoteDataSource {

    override suspend fun getResultSearchImage(
        apiKey: String,
        keyWord: String,
        page: Int,
        size: Int,
    ): ResultSearchImage {
        return searchService.getSearchImage(apiKey, keyWord, "recency", page, size)
    }

    override suspend fun getResultSearchVClip(
        apiKey: String,
        keyWord: String,
        page: Int,
        size: Int,
    ): ResultSearchVClip {
        return searchService.getSearchVClip(apiKey, keyWord, "recency", page, size)
    }
}