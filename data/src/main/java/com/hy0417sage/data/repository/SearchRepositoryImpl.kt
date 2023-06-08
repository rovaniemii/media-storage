package com.hy0417sage.data.repository

import com.hy0417sage.core.model.SearchItem
import com.hy0417sage.data.repository.datasource.RemoteDataSource
import com.hy0417sage.data.remote.mapper.imageMapper
import com.hy0417sage.data.remote.mapper.totalMapper
import com.hy0417sage.data.remote.mapper.vClipMapper
import com.hy0417sage.domain.repository.SearchRepository
import javax.inject.Inject

/**
 * Domain Layer 의 Repository Interface 구현부.
 * DataSource 를 인자로 받아 컨트롤 하여 필요한 Data 를 가져옴.
 *
 * @param remoteDataSource api 에서 가져오는 Image Data
 * @param vClipRemoteDataSource api 에서 가져오는 VClip Data
 */

class SearchRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
) : SearchRepository {

    // 키워드 검색
    override suspend fun getSearchImage(query: String, page: Int): List<SearchItem> {
        val data = kotlin.runCatching {
            totalMapper(
                imageMapper(
                    remoteDataSource.getResultSearchImage(
                        KAKAO_KEY,
                        query,
                        page).documents)
                        + vClipMapper(
                    remoteDataSource.getResultSearchVClip(
                        KAKAO_KEY,
                        query,
                        page).documents))
        }.getOrDefault(listOf())
        return data
    }

    companion object {
        const val KAKAO_KEY = "KakaoAK a0f922e7fe4906b06e508e9eee4a4827"
    }
}