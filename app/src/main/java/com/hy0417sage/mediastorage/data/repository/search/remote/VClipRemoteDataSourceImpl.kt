package com.hy0417sage.mediastorage.data.repository.search.remote

import com.hy0417sage.mediastorage.data.api.KakaoAPI
import com.hy0417sage.mediastorage.data.model.search.ResultSearchVClip
import javax.inject.Inject

/**
 * VClip - DataSource 에서 선언한 Interface 의 구현부.
 * 해당 Interface 를 상속받아 사용한다.
 */

class VClipRemoteDataSourceImpl @Inject constructor(
    private val kakaoAPI: KakaoAPI,
) : VClipRemoteDataSource {

    override suspend fun getResultSearchVClip(
        apiKey: String,
        keyWord: String,
        page: Int,
        size: Int,
    ): ResultSearchVClip {
        return kakaoAPI.getSearchVClip(apiKey, keyWord, "recency", page, size)
    }
}