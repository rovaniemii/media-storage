package com.hy0417sage.data.remote.service

import com.hy0417sage.data.remote.model.ApiImagesResponse
import com.hy0417sage.data.remote.model.ApiVideosResponse
import com.hy0417sage.core.data.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
    @GET("/v2/search/image")
    suspend fun searchImages(
        @Query("sort") sort : String,
        @Query("query") query : String,
        @Query("page") page : Int,
        @Query("size") size : Int,
    ): BaseResponse<ApiImagesResponse>

    @GET("/v2/search/vclip")
    suspend fun searchVideos(
        @Query("sort") sort : String,
        @Query("query") query : String,
        @Query("page") page : Int,
        @Query("size") size : Int,
    ): BaseResponse<ApiVideosResponse>
}