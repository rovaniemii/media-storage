package com.hy0417sage.mediastorage.data.repository.remote

import com.hy0417sage.mediastorage.data.api.KakaoAPI
import com.hy0417sage.mediastorage.data.model.ResultSearchImage
import com.hy0417sage.mediastorage.data.model.ResultSearchVClip
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val kakaoAPI: KakaoAPI,
) : RemoteDataSource {

    override suspend fun getResultSearchImage(
        apiKey: String,
        keyWord: String,
        page: Int,
        size: Int,
    ): ResultSearchImage {
        return kakaoAPI.getSearchImage(apiKey, keyWord, "recency", page, size)
    }

    override suspend fun getResultSearchVClip(
        apiKey: String,
        keyWord: String,
        page: Int,
        size: Int,
    ): ResultSearchVClip {
        return kakaoAPI.getSearchVClip(apiKey, keyWord, "recency", page, size)
    }
}
