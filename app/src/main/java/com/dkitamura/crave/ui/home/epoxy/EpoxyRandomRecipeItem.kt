package com.dkitamura.crave.ui.home.epoxy

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.Nullable
import coil.api.load
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.dkitamura.crave.databinding.EpoxyLoaderViewBinding
import com.dkitamura.crave.databinding.EpoxyRandomRecipeItemBinding

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class EpoxyRandomRecipeItem  @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(
    context,
    attributeSet,
    defStyle) {

    private var binding: EpoxyRandomRecipeItemBinding =
        EpoxyRandomRecipeItemBinding.inflate(LayoutInflater.from(context), this, true)

    private var recipeId = -1

    @ModelProp
    fun setRecipeId(id: Int) {
       this.recipeId = id
    }

    @TextProp // Use this annotation for text.
    fun setTitle(text: CharSequence) {
        binding.titleText.text = text
    }

    @TextProp
    fun setDescription(text: CharSequence) {
        binding.descriptionText.text = text
    }

    @ModelProp
    fun setImage(imageUrl: String) {
        binding.recipeImage.load(imageUrl)
    }

    @CallbackProp // Use this annotation for click listeners or other callbacks.
     fun clickListener(@Nullable listener: RecipeClickListener?) {
        setOnClickListener {
            listener?.onRecipeClicked(recipeId)
        }
    }
}