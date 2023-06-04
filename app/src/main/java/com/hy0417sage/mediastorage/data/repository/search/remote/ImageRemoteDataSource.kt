package com.hy0417sage.mediastorage.data.repository.search.remote

import com.hy0417sage.mediastorage.data.model.search.ResultSearchImage

/**
 * Api 호출을 통해 Image Data 를 가져오기 위한 interface
 * Image - DataSourceImpl 에서 구현된다.
 */

interface ImageRemoteDataSource {

    suspend fun getResultSearchImage(
        apiKey: String,
        keyWord: String,
        page: Int,
        size: Int,
    ): ResultSearchImage
}