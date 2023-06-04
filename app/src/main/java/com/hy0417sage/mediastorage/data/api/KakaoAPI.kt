package com.hy0417sage.mediastorage.data.api

import com.hy0417sage.mediastorage.data.model.search.ResultSearchImage
import com.hy0417sage.mediastorage.data.model.search.ResultSearchVClip
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface KakaoAPI {

    @GET("/v2/search/image?")
    suspend fun getSearchImage(
        @Header("Authorization") key: String,
        @Query("query") query: String,
        @Query("sort") sort: String,
        @Query("page") page: Int,
        @Query("size") size: Int,
    ): ResultSearchImage

    @GET("/v2/search/vclip?")
    suspend fun getSearchVClip(
        @Header("Authorization") key: String,
        @Query("query") query: String,
        @Query("sort") sort: String,
        @Query("page") page: Int,
        @Query("size") size: Int,
    ): ResultSearchVClip

}