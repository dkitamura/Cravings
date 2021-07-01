package com.dkitamura.crave.ui.home

import android.util.Log
import android.view.View
import androidx.core.text.parseAsHtml
import com.airbnb.epoxy.EpoxyController
import com.dkitamura.crave.models.network.randomrecipes.Recipe
import com.dkitamura.crave.ui.home.epoxy.RecipeClickListener
import com.dkitamura.crave.ui.home.epoxy.epoxyLoaderView
import com.dkitamura.crave.ui.home.epoxy.epoxyRandomRecipeItem
import com.dkitamura.crave.ui.home.epoxy.homeTextView

class HomeEpoxyController(
    val recipeClickListener: RecipeClickListener
): EpoxyController() {

    private var recipes: List<Recipe> = ArrayList()
    private var loading = true

    override fun buildModels() {

        homeTextView {
            id("TITLE")
            title("Search recipes above.  Need ideas? Why not try these below")
        }

        if(loading) {
            epoxyLoaderView {
                id("loaderview")
            }
        } else {
            for (recipe in recipes) {

                epoxyRandomRecipeItem {
                    id(recipe.hashCode())

                    recipeId(recipe.id ?: -1)

                    title(recipe.title ?: "")

                    //We need to grab the first element and see if it is blank.
                    //This is the consequence of parsing List<String> for Cuisines into Room
                    if(recipe.cuisines.isNotEmpty() && recipe.cuisines.first().isNotEmpty()) {
                        description("Cuisines: ${recipe.cuisines.joinToString()}")
                    } else {
                        description("Cuisines: N/A")
                    }

                    image(recipe.image ?: "")

                    clickListener(recipeClickListener)
                }
            }
        }
    }

    fun setRecipeList(
        recipeList: List<Recipe> = emptyList()
    ) {
        recipes = recipeList
        requestModelBuild()
    }

    fun setLoadingStatus(
        status: Boolean = false
    ) {
        loading = status
        requestModelBuild()
    }

}