package com.hy0417sage.mediastorage.data.repository

import com.hy0417sage.mediastorage.data.repository.remote.RemoteDataSource
import com.hy0417sage.mediastorage.BuildConfig
import com.hy0417sage.mediastorage.data.mapper.imageMapper
import com.hy0417sage.mediastorage.data.mapper.totalMapper
import com.hy0417sage.mediastorage.data.mapper.vClipMapper
import com.hy0417sage.mediastorage.domain.model.ViewData
import com.hy0417sage.mediastorage.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
) : Repository {

    override suspend fun getSearchData(query: String, page: Int, size: Int): List<ViewData> {
        val data = kotlin.runCatching {
            totalMapper(
                imageMapper(
                    remoteDataSource.getResultSearchImage(
                        KAKAO_KEY,
                        query,
                        page,
                        size).documents)
                        + vClipMapper(
                    remoteDataSource.getResultSearchVClip(
                        KAKAO_KEY,
                        query,
                        page,
                        size).documents))
        }.getOrDefault(listOf())
        return data
    }

    companion object {
        const val KAKAO_KEY = BuildConfig.KAKAO_KEY
    }
}