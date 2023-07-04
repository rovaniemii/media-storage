package com.hy0417sage.data.remote.service

import com.hy0417sage.core.util.Constants.AUTH_HEADER
import okhttp3.Interceptor
import okhttp3.Response
/**
 * HeaderInterceptor 에서 인증키를 세팅함으로 api 호출마다 헤더에 인증키 값을 넣어주지 않아도 된다.
 * */
class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader("Authorization", AUTH_HEADER)
                .build()
        )
    }
}