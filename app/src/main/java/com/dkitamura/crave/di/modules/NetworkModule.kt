package com.dkitamura.crave.di.modules

import com.dkitamura.crave.BuildConfig
import com.dkitamura.crave.network.AuthInterceptor
import com.dkitamura.crave.network.RecipeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideBaseUrl() = "https://api.spoonacular.com/"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        var builder = OkHttpClient.Builder()

        if(BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            builder.addNetworkInterceptor(logging)
        }

        builder.addNetworkInterceptor(AuthInterceptor(BuildConfig.API_KEY))

        return builder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        BASE_URL: String
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideRecipeApi(retrofit: Retrofit): RecipeApi = retrofit.create(RecipeApi::class.java)
}