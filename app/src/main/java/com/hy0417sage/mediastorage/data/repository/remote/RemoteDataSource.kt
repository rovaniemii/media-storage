package com.hy0417sage.mediastorage.data.repository.remote

import com.hy0417sage.mediastorage.data.model.ResultSearchImage
import com.hy0417sage.mediastorage.data.model.ResultSearchVClip

interface RemoteDataSource {

    suspend fun getResultSearchImage(
        apiKey: String,
        keyWord: String,
    ): ResultSearchImage

    suspend fun getResultSearchVClip(
        apiKey: String,
        keyWord: String,
    ): ResultSearchVClip

}