package com.dkitamura.crave.models.network.recipeinformation


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WinePairing(
    @Json(name = "pairedWines")
    val pairedWines: List<String>?,
    @Json(name = "pairingText")
    val pairingText: String?,
    @Json(name = "productMatches")
    val productMatches: List<ProductMatche>?
)