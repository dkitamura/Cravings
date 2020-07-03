package com.dkitamura.crave

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.dkitamura.crave.network.RecipeApi
import com.dkitamura.crave.ui.home.HomeViewModel

class ViewModelFactory(
    owner: SavedStateRegistryOwner,
    private val api: RecipeApi,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
    override fun <T : ViewModel> create(
        key: String, modelClass: Class<T>, handle: SavedStateHandle
    ): T {
        return HomeViewModel(api) as T
    }
}