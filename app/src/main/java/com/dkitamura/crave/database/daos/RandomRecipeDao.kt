package com.dkitamura.crave.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.dkitamura.crave.models.network.randomrecipes.Recipe
import kotlinx.coroutines.flow.Flow

@Dao
interface RandomRecipeDao {

    @Query("SELECT * FROM recipe")
    fun getAllRecipes(): Flow<List<Recipe>>

    @Insert
    suspend fun insertAll(recipes: List<Recipe>)

    @Delete
    suspend fun deleteByRecipe(recipe: Recipe)

    @Query("DELETE FROM recipe")
    suspend fun deleteAllEntries()

}