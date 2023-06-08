package com.hy0417sage.data.remote.service

import com.hy0417sage.data.remote.model.ResultSearchImage
import com.hy0417sage.data.remote.model.ResultSearchVClip
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface SearchService {

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