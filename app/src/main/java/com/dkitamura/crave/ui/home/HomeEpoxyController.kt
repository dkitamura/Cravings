package com.dkitamura.crave.ui.home

import androidx.core.text.parseAsHtml
import com.airbnb.epoxy.EpoxyController
import com.dkitamura.crave.models.network.randomrecipes.Recipe
import com.dkitamura.crave.ui.home.epoxy.epoxyLoaderView
import com.dkitamura.crave.ui.home.epoxy.epoxyRandomRecipeItem
import com.dkitamura.crave.ui.home.epoxy.homeTextView

class HomeEpoxyController: EpoxyController() {

    private var recipes: MutableList<Recipe> = ArrayList()
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

                    title(recipe.title ?: "")

                    if(recipe.cuisines?.isNotEmpty() == true) {
                        description("Cuisines: ${recipe.cuisines.joinToString()}")
                    } else {
                        description("Cuisines: Not available")
                    }

                    image(recipe.image ?: "")
                }
            }
        }
    }

    fun setValues(
        isLoading: Boolean = false,
        recipeList: List<Recipe> = emptyList()
    ) {
        loading = isLoading
        recipes = recipeList.toMutableList()

        requestModelBuild()
    }

}