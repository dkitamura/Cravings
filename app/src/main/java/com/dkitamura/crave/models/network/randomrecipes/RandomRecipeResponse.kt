package com.dkitamura.crave.models.network.randomrecipes


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RandomRecipeResponse(
    @Json(name = "recipes")
    val recipes: List<Recipe>?
)