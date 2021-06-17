package com.dkitamura.crave.models.network.recipeinformation


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Metric(
    @Json(name = "amount")
    val amount: Double?,
    @Json(name = "unitLong")
    val unitLong: String?,
    @Json(name = "unitShort")
    val unitShort: String?
)