package com.hy0417sage.mediastorage.data.repository.remote

import com.hy0417sage.mediastorage.data.model.ResultSearchImage
import com.hy0417sage.mediastorage.data.model.ResultSearchVClip

interface RemoteDataSource {

    suspend fun getResultSearchImage(
        apiKey: String,
        keyWord: String,
        page: Int,
        size: Int,
    ): ResultSearchImage

    suspend fun getResultSearchVClip(
        apiKey: String,
        keyWord: String,
        page: Int,
        size: Int,
    ): ResultSearchVClip

}