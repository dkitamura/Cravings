package com.dkitamura.crave.ui.home.epoxy

import com.dkitamura.crave.databinding.EpoxyLoaderViewBinding


import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.dkitamura.crave.R
import com.dkitamura.crave.databinding.EpoxyHomeTextviewBinding

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_MATCH_HEIGHT)
class EpoxyLoaderView  @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(
    context,
    attributeSet,
    defStyle) {

    private var binding: EpoxyLoaderViewBinding =
        EpoxyLoaderViewBinding.inflate(LayoutInflater.from(context), this, true)

}