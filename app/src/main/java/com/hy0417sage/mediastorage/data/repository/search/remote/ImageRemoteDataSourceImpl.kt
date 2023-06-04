package com.hy0417sage.mediastorage.data.repository.search.remote

import com.hy0417sage.mediastorage.data.api.KakaoAPI
import com.hy0417sage.mediastorage.data.model.search.ResultSearchImage
import javax.inject.Inject

/**
 * Image - DataSource 에서 선언한 Interface 의 구현부.
 * 해당 Interface 를 상속받아 사용한다.
 */

class ImageRemoteDataSourceImpl @Inject constructor(
    private val kakaoAPI: KakaoAPI,
) : ImageRemoteDataSource {

    override suspend fun getResultSearchImage(
        apiKey: String,
        keyWord: String,
        page: Int,
        size: Int,
    ): ResultSearchImage {
        return kakaoAPI.getSearchImage(apiKey, keyWord, "recency", page, size)
    }
}