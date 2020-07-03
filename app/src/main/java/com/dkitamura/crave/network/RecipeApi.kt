package com.dkitamura.crave.network

import com.dkitamura.crave.models.network.randomrecipes.RandomRecipeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeApi {

    @GET("recipes/random")
    suspend fun getRandomRecipes(@Query("number") number: Int): Response<RandomRecipeResponse>
}