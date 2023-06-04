package com.hy0417sage.mediastorage.data.repository.search.remote

import com.hy0417sage.mediastorage.data.model.search.ResultSearchVClip

/**
 * Api 호출을 통해 VClip Data 를 가져오기 위한 interface
 * VClip - DataSourceImpl 에서 구현된다.
 */

interface VClipRemoteDataSource {

    suspend fun getResultSearchVClip(
        apiKey: String,
        keyWord: String,
        page: Int,
        size: Int,
    ): ResultSearchVClip
}