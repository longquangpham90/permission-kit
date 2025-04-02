package com.studio.common.ui.extensions

import android.view.View
import android.view.ViewTreeObserver
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.TranslateAnimation
import android.view.inputmethod.InputMethodManager
import androidx.core.content.getSystemService
import androidx.core.view.isVisible

fun View.hideKeyboard() {
    val imm = this.context.getSystemService<InputMethodManager>()
    imm?.hideSoftInputFromWindow(this.windowToken, 0)
}

fun View.slideUp(onAnimationDone: () -> Unit) {
    isVisible = true
    val animate =
        TranslateAnimation(
            0F, // fromXDelta
            0F, // toXDelta
            height.toFloat(), // fromYDelta
            0F
        )
    ; // toYDelta
    animate.setAnimationListener(
        object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {
            }

            override fun onAnimationEnd(p0: Animation?) {
                isVisible = true
                onAnimationDone.invoke()
            }

            override fun onAnimationRepeat(p0: Animation?) {
            }
        }
    )
    animate.duration = 300
    startAnimation(animate)
}

fun View.slideDown(onAnimationDone: () -> Unit) {
    val animate =
        TranslateAnimation(
            0F, // fromXDelta
            0F, // toXDelta
            0F,
            (height).toFloat()
        )
    ; // toYDelta
    animate.setAnimationListener(
        object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {
            }

            override fun onAnimationEnd(p0: Animation?) {
                isVisible = false
                onAnimationDone.invoke()
            }

            override fun onAnimationRepeat(p0: Animation?) {
            }
        }
    )
    animate.duration = 300
    startAnimation(animate)
}

fun View.slideUpHide() {
    val animate =
        TranslateAnimation(
            0F, // fromXDelta
            -width.toFloat(), // toXDelta
            0F, // fromYDe
            -height.toFloat() / 2
        ) // toYDelta
    animate.setAnimationListener(
        object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {
            }

            override fun onAnimationEnd(p0: Animation?) {
                isVisible = false
            }

            override fun onAnimationRepeat(p0: Animation?) {
            }
        }
    )
    animate.duration = 300
    startAnimation(animate)
}

fun View.slideDown() {
    isVisible = true
    val animate =
        TranslateAnimation(
            width.toFloat(), // fromXDelta
            0F, // toXDelta
            0F,
            0F
        )
    ; // toYDelta
    animate.setAnimationListener(
        object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {
            }

            override fun onAnimationEnd(p0: Animation?) {
                isVisible = true
            }

            override fun onAnimationRepeat(p0: Animation?) {
            }
        }
    )
    animate.duration = 300
    startAnimation(animate)
}

inline fun View.afterMeasured(crossinline block: () -> Unit) {
    if (measuredWidth > 0 && measuredHeight > 0) {
        block()
    } else {
        viewTreeObserver.addOnGlobalLayoutListener(
            object : ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    if (measuredWidth > 0 && measuredHeight > 0) {
                        viewTreeObserver.removeOnGlobalLayoutListener(this)
                        block()
                    }
                }
            }
        )
    }
}

fun View.anim(
    anim: Int,
    startListener: () -> Unit,
    endListener: () -> Unit
) {
    val viewAnim =
        AnimationUtils.loadAnimation(
            context,
            anim
        )
    viewAnim.setAnimationListener(
        object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
                startListener.invoke()
            }

            override fun onAnimationRepeat(animation: Animation) {
                Unit
            }

            override fun onAnimationEnd(animation: Animation) {
                setAnimation(null)
                endListener.invoke()
            }
        }
    )
    startAnimation(viewAnim)
}
