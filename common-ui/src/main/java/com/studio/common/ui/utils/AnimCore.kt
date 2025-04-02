package com.studio.common.ui.utils

import android.content.Context
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView

object AnimCore {
    fun start(ctx: Context?, anim: Int, view: ImageView) {
        val rotate = AnimationUtils.loadAnimation(ctx, anim)
        view.visibility = View.VISIBLE
        view.startAnimation(rotate)
    }

    fun stop(view: ImageView) {
        view.animation = null
        view.visibility = View.INVISIBLE
    }

    fun anim(
        view: View,
        anim: Int,
        hide: Boolean,
        duration: Long
    ) {
        val viewAnim = AnimationUtils.loadAnimation(
            view.context,
            anim
        )
        viewAnim.duration = duration
        viewAnim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
            }

            override fun onAnimationRepeat(animation: Animation) {
            }

            override fun onAnimationEnd(animation: Animation) {
                if (hide) view.visibility = View.GONE else view.visibility = View.VISIBLE
            }
        })
        view.startAnimation(viewAnim)
    }
}
