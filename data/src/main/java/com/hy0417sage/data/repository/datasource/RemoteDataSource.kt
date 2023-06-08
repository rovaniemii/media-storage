package com.hy0417sage.data.repository.datasource

import com.hy0417sage.data.remote.model.ResultSearchImage
import com.hy0417sage.data.remote.model.ResultSearchVClip


/**
 * Api 호출을 통해 Data 를 가져오기 위한 interface
 * Image, VClip - DataSourceImpl 에서 구현된다.
 */

interface RemoteDataSource {

    suspend fun getResultSearchImage(
        apiKey: String,
        keyWord: String,
        page: Int,
        size: Int = 10,
    ): ResultSearchImage

    suspend fun getResultSearchVClip(
        apiKey: String,
        keyWord: String,
        page: Int,
        size: Int = 10,
    ): ResultSearchVClip
}