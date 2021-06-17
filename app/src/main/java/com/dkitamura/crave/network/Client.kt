package com.dkitamura.crave.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


class Client(val apiKey: String) {

    fun build(): RecipeApi {
        val retrofit = Retrofit.Builder()
            .client(buildOkHttpClient(apiKey))
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        return retrofit.create(RecipeApi::class.java)
    }

    private fun buildOkHttpClient(apiKey: String): OkHttpClient {
        val client = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(apiKey))
            .callTimeout(10, TimeUnit.SECONDS)
            .build()
        return client
    }
}