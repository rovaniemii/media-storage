package com.hy0417sage.mediastorage.data.repository

import android.util.Log
import com.hy0417sage.mediastorage.data.repository.remote.RemoteDataSource
import com.hy0417sage.mediastorage.BuildConfig
import com.hy0417sage.mediastorage.data.mapper.imageMapper
import com.hy0417sage.mediastorage.data.mapper.totalMapper
import com.hy0417sage.mediastorage.data.mapper.vClipMapper
import com.hy0417sage.mediastorage.domain.model.ViewData
import com.hy0417sage.mediastorage.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.zip
import retrofit2.HttpException
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
) : Repository {

    override fun getSearchData(query: String): Flow<List<ViewData>> {
        return flow {
            try {
                remoteDataSource.getResultSearchImage(KAKAO_KEY, query).zip(
                    remoteDataSource.getResultSearchVClip(KAKAO_KEY, query)
                ) { imageResult, vClipResult ->
                    emit(totalMapper(imageMapper(imageResult.documents) + vClipMapper(vClipResult.documents)))
                }.collect()
            } catch (e: HttpException) {
                if (e.code() == 400) {
                    Log.d("Repository", " HTTP 400 ")
                }
            }
        }
    }

    companion object {
        const val KAKAO_KEY = BuildConfig.KAKAO_KEY
    }
}