package com.dkitamura.crave.ui.home.epoxy

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.dkitamura.crave.R
import com.dkitamura.crave.databinding.EpoxyHomeTextviewBinding

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class HomeTextView  @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(
    context,
    attributeSet,
    defStyle) {

    private var binding: EpoxyHomeTextviewBinding =
        EpoxyHomeTextviewBinding.inflate(LayoutInflater.from(context), this, true)

    @TextProp // Use this annotation for text.
    fun setTitle(text: CharSequence) {
        binding.textview.text = text
    }
}