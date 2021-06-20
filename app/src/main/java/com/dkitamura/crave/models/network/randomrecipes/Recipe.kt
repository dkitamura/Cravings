package com.dkitamura.crave.models.network.randomrecipes


import androidx.room.*
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import org.threeten.bp.OffsetDateTime

@JsonClass(generateAdapter = true)
@Entity(tableName = "recipe")
data class Recipe(
    @Json(name = "aggregateLikes")
    @ColumnInfo(name = "aggregate_likes")
    var aggregateLikes: Int = 0,

    @Json(name = "cheap")
    @ColumnInfo(name = "cheap")
    var cheap: Boolean = false,

    @Json(name = "creditsText")
    @ColumnInfo(name = "credits_text")
    var creditsText: String = "",

    @Json(name = "cuisines")
    @ColumnInfo(name = "cuisines")
    var cuisines: List<String> = emptyList(),

    @Json(name = "dairyFree")
    @ColumnInfo(name = "dairy_free")
    var dairyFree: Boolean = false,

    @Json(name = "glutenFree")
    @ColumnInfo(name = "gluten_free")
    var glutenFree: Boolean = false,

    @Json(name = "healthScore")
    @ColumnInfo(name = "health_score")
    var healthScore: Double = 0.0,

    @Json(name = "id")
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = false)
    var id: Int = -1,

    @Json(name = "image")
    @ColumnInfo(name = "image_url")
    var image: String = "",

    @Json(name = "imageType")
    @ColumnInfo(name = "image_type")
    var imageType: String = "",

    @Json(name = "instructions")
    @ColumnInfo(name = "instructions")
    var instructions: String = "",

    @Json(name = "license")
    @ColumnInfo(name = "license")
    var license: String = "",

    @Json(name = "lowFodmap")
    @ColumnInfo(name = "low_fod_map")
    var lowFodmap: Boolean = false,

    @Json(name = "pricePerServing")
    @ColumnInfo(name = "price_per_serving")
    var pricePerServing: Double = 0.0,

    @Json(name = "readyInMinutes")
    @ColumnInfo(name = "ready_in_minutes")
    var readyInMinutes: Int = -1,

    @Json(name = "servings")
    @ColumnInfo(name = "servings")
    var servings: Int = -1,

    @Json(name = "sourceName")
    @ColumnInfo(name = "source_name")
    var sourceName: String = "",

    @Json(name = "sourceUrl")
    @ColumnInfo(name = "source_url")
    var sourceUrl: String = "",

    @Json(name = "spoonacularScore")
    @ColumnInfo(name = "spoonacular_score")
    var spoonacularScore: Double = 0.0,

    @Json(name = "spoonacularSourceUrl")
    @ColumnInfo(name = "spoonacularUrl")
    var spoonacularSourceUrl: String = "",

    @Json(name = "summary")
    @ColumnInfo(name = "summary")
    var summary: String = "",

    @Json(name = "sustainable")
    @ColumnInfo(name = "sustainable")
    var sustainable: Boolean = false,

    @Json(name = "title")
    @ColumnInfo(name = "title")
    var title: String = "",

    @Json(name = "vegan")
    @ColumnInfo(name = "vegan")
    var vegan: Boolean = false,

    @Json(name = "vegetarian")
    @ColumnInfo(name = "vegetarian")
    var vegetarian: Boolean = false,

    @Json(name = "veryHealthy")
    @ColumnInfo(name = "very_healthy")
    var veryHealthy: Boolean = false,

    @ColumnInfo(name = "created_at")
    var createdAt: OffsetDateTime = OffsetDateTime.now()
)