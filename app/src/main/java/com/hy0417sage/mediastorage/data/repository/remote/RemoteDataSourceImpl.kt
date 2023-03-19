package com.hy0417sage.mediastorage.data.repository.remote

import com.hy0417sage.mediastorage.data.api.KakaoAPI
import com.hy0417sage.mediastorage.data.model.ResultSearchImage
import com.hy0417sage.mediastorage.data.model.ResultSearchVClip
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val kakaoAPI: KakaoAPI,
) : RemoteDataSource {

    override suspend fun getResultSearchImage(
        apiKey: String,
        keyWord: String,
    ): Flow<ResultSearchImage> {
        return flow {
            emit(kakaoAPI.getSearchImage(apiKey, keyWord))
        }
    }

    override suspend fun getResultSearchVClip(
        apiKey: String,
        keyWord: String,
    ): Flow<ResultSearchVClip> {
        return flow {
            emit(kakaoAPI.getSearchVClip(apiKey, keyWord))
        }
    }
}