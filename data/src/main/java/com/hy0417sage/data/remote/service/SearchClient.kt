package com.hy0417sage.data.remote.service

import com.hy0417sage.core.util.Constants.BASE_URL
import com.hy0417sage.data.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SearchClient {
    fun create(): SearchService {

        val client = OkHttpClient.Builder().apply {
            retryOnConnectionFailure(true) /* 연결에 문제 발생시 재시도 */
            addInterceptor(HeaderInterceptor()) /* 인증키 초기 세팅 */
            addInterceptor(HttpLoggingInterceptor().apply {
                if (BuildConfig.DEBUG) {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            }) /* 디버그 상태에서 http 통신 로깅 */

        }.build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SearchService::class.java)
    }
}