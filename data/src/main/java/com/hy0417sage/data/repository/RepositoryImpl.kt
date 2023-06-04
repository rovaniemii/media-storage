package com.hy0417sage.data.repository

import com.hy0417sage.data.repository.remote.ImageRemoteDataSource
import com.hy0417sage.data.mapper.imageMapper
import com.hy0417sage.data.mapper.totalMapper
import com.hy0417sage.data.mapper.vClipMapper
import com.hy0417sage.data.repository.remote.VClipRemoteDataSource
import com.hy0417sage.domain.model.ViewData
import com.hy0417sage.domain.repository.Repository
import javax.inject.Inject

/**
 * Domain Layer 의 Repository Interface 구현부.
 * DataSource 를 인자로 받아 컨트롤 하여 필요한 Data 를 가져옴.
 *
 * @param imageRemoteDataSource api 에서 가져오는 Image Data
 * @param vClipRemoteDataSource api 에서 가져오는 VClip Data
 */

class RepositoryImpl @Inject constructor(
    private val imageRemoteDataSource: ImageRemoteDataSource,
    private val vClipRemoteDataSource: VClipRemoteDataSource,
) : Repository {

    // 키워드 검색
    override suspend fun getSearchData(query: String, page: Int, size: Int): List<ViewData> {
        val data = kotlin.runCatching {
            totalMapper(
                imageMapper(
                    imageRemoteDataSource.getResultSearchImage(
                        KAKAO_KEY,
                        query,
                        page,
                        size).documents)
                        + vClipMapper(
                    vClipRemoteDataSource.getResultSearchVClip(
                        KAKAO_KEY,
                        query,
                        page,
                        size).documents))
        }.getOrDefault(listOf())
        return data
    }

    companion object {
        const val KAKAO_KEY = "KakaoAK a0f922e7fe4906b06e508e9eee4a4827"
    }
}