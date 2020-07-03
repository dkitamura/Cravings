package com.dkitamura.crave.models.network.randomrecipes


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ExtendedIngredient(
    @Json(name = "aisle")
    val aisle: String?,
    @Json(name = "amount")
    val amount: Double?,
    @Json(name = "consistency")
    val consistency: String?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "image")
    val image: String?,
    @Json(name = "measures")
    val measures: Measures?,
    @Json(name = "meta")
    val meta: List<String>?,
    @Json(name = "metaInformation")
    val metaInformation: List<String>?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "original")
    val original: String?,
    @Json(name = "originalName")
    val originalName: String?,
    @Json(name = "originalString")
    val originalString: String?,
    @Json(name = "unit")
    val unit: String?
)