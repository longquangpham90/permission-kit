package com.studio.common.ui.binding

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.studio.common.ui.extensions.slideDown
import com.studio.common.ui.extensions.slideUp
import com.studio.common.ui.extensions.slideUpHide
import com.studio.common.ui.utils.OnSingleClickListener

@BindingAdapter("isSelected")
fun View.isSelected(isSelected: Boolean) {
    this.isSelected = isSelected
}

@BindingAdapter("isEnabled")
fun View.setEnabled(isEnabled: Boolean) {
    this.isEnabled = isEnabled
}

@BindingAdapter("isGone")
fun View.setGone(isGone: Boolean) {
    this.visibility = if (isGone) View.GONE else View.VISIBLE
}

@BindingAdapter("isInvisiable")
fun View.setIsInvisible(isInvisible: Boolean) {
    this.isInvisible = isInvisible
}

fun View.isSlideUpDown(isGone: Boolean, onAnimationDone: () -> Unit) {
    if (isGone) {
        this.slideDown(onAnimationDone)
    } else {
        this.slideUp(onAnimationDone)
    }
}

fun View.isSlideUpDown2(isGone: Boolean, needShowAnimation: Boolean? = false) {
    if (isGone) {
        this.slideUpHide()
    } else {
        isVisible = true
    }
}

@BindingAdapter("isVisible")
fun View.setVisible(isVisible: Boolean) {
    this.visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
}

@BindingAdapter("onSingleClick")
fun View.setOnSingleClickListener(clickListener: View.OnClickListener?) {
    clickListener?.also {
        setOnClickListener(OnSingleClickListener(it))
    } ?: setOnClickListener(null)
}

@BindingAdapter("onSingleClick", "singleClickTime", requireAll = false)
fun View.setOnSingleClickListener(clickListener: View.OnClickListener?, time: Long? = null) {
    clickListener?.also {
        setOnClickListener(OnSingleClickListener(it, time))
    } ?: setOnClickListener(null)
}

@BindingAdapter("customWidth")
fun View.setLayoutWidth(width: Float) {
    this.layoutParams = this.layoutParams.apply {
        this.width = width.toInt()
    }
}

@BindingAdapter("customHeight")
fun View.setLayoutHeight(height: Float) {
    this.layoutParams = this.layoutParams.apply {
        this.height = height.toInt()
    }
}

@BindingAdapter("ratio_video")
fun View.setLayoutRatio(ratio: String?) {
    ratio.let {
        val layoutParams = this.layoutParams as ConstraintLayout.LayoutParams
        layoutParams.dimensionRatio = ratio
        this.layoutParams = layoutParams
    }
}
