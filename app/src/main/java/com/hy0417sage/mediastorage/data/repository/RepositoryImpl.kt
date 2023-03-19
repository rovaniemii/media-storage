package com.hy0417sage.mediastorage.data.repository

import com.hy0417sage.mediastorage.data.repository.remote.RemoteDataSource
import com.hy0417sage.mediastorage.BuildConfig
import com.hy0417sage.mediastorage.data.mapper.imageMapper
import com.hy0417sage.mediastorage.data.mapper.vClipMapper
import com.hy0417sage.mediastorage.domain.model.ViewData
import com.hy0417sage.mediastorage.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
) : Repository {

    override fun getSearchFlow(query: String): Flow<List<ViewData>> {
        return flow {
            remoteDataSource.getResultSearchImage(KAKAO_KEY, query).collect {
                emit(imageMapper(it.documents))
            }
            remoteDataSource.getResultSearchVClip(KAKAO_KEY, query).collect{
                emit(vClipMapper(it.documents))
            }
        }
    }

    companion object{
        const val KAKAO_KEY = BuildConfig.KAKAO_KEY
    }
}