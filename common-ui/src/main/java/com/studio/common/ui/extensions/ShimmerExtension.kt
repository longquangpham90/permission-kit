package com.studio.common.ui.extensions

import androidx.databinding.BindingAdapter
import com.facebook.shimmer.ShimmerFrameLayout

@BindingAdapter("autoStart", requireAll = false)
fun ShimmerFrameLayout.initBinding(
    autoStart: Boolean?
) {
    autoStart?.let {
        if (autoStart) {
            showShimmer(true)
            startShimmer()
        } else {
            setBackgroundResource(android.R.color.transparent)
            hideShimmer()
        }
    }
}
