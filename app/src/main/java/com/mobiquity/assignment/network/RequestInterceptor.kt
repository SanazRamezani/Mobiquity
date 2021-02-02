package com.mobiquity.assignment.network

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val authorizedRequest: Request = request
            .newBuilder()
            .url(request.url.toString())
            .build()
        return chain.proceed(authorizedRequest)
    }
}