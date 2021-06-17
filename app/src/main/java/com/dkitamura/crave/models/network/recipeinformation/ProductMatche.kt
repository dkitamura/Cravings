package com.dkitamura.crave.models.network.recipeinformation


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductMatche(
    @Json(name = "averageRating")
    val averageRating: Double?,
    @Json(name = "description")
    val description: String?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "imageUrl")
    val imageUrl: String?,
    @Json(name = "link")
    val link: String?,
    @Json(name = "price")
    val price: String?,
    @Json(name = "ratingCount")
    val ratingCount: Double?,
    @Json(name = "score")
    val score: Double?,
    @Json(name = "title")
    val title: String?
)