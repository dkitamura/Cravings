package com.dkitamura.crave.network

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = request.url.newBuilder().addQueryParameter("apiKey", apiKey).build()

        request = request.newBuilder().url(url).build()

        return chain.proceed(request)
    }
}