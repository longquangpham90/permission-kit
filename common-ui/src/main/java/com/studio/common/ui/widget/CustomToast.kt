package com.studio.common.ui.widget

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Handler
import android.os.Looper
import android.view.GestureDetector
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.PopupWindow
import com.studio.common.ui.R
import com.studio.common.ui.databinding.ViewToastCustomBinding
import com.studio.common.ui.extensions.dp2px
import com.studio.common.ui.extensions.initBackground
import com.studio.common.ui.extensions.initToastStyle
import com.studio.common.ui.extensions.orZero
import com.studio.common.ui.model.ToastData
import com.studio.common.ui.utils.ScreenUtils
import timber.log.Timber

// Refer: https://github.com/shasin89/NotificationBanner
class CustomToast {
    private var showBanner = false
    private var asDropDown = false
    private var animationStyle: Int? = null
    private var gravity: Int = Gravity.TOP
    private val handler = Handler(Looper.getMainLooper())

    @SuppressLint("ClickableViewAccessibility")
    companion object {
        private var activity: Activity? = null
        private var rootView: View? = null
        private var instance: CustomToast? = null
        var toastData: ToastData? = null

        val binding by lazy {
            ViewToastCustomBinding.inflate(
                LayoutInflater.from(activity),
                null,
                false
            )
        }
        private val popupWindow by lazy {
            val widthScreen = ScreenUtils.getScreenWidth(binding.root.context)
            val width = widthScreen - dp2px(18.0F)
            var height = LinearLayout.LayoutParams.WRAP_CONTENT
            PopupWindow(binding.root, width, height, false)
        }

        fun make(
            view: View,
            activity: Activity,
            toastData: ToastData?
        ): CustomToast {
            if (instance == null) {
                instance = CustomToast()
            } else {
                if (instance?.showBanner == true) {
                    instance?.dismissToast()
                }
            }
            this.activity = activity
            this.rootView = view
            this.toastData = toastData
            instance?.apply {
                setAnimationStyle()
            }
            return instance!!
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    fun show() {
        activity?.let {
            showBanner = true
            animationStyle?.let {
                popupWindow.setBackgroundDrawable(null)
                popupWindow.elevation = 2F
                popupWindow.windowLayoutType =
                    WindowManager.LayoutParams.TYPE_APPLICATION_ATTACHED_DIALOG
                popupWindow.animationStyle = it
            }
            binding.apply {
                toastData = CustomToast.toastData
                imageViewStart.setImageResource(CustomToast.toastData?.startDrawable.orZero())
                constraintLayoutToast.initBackground(CustomToast.toastData?.isSuccess)
                textViewMessage.initToastStyle(CustomToast.toastData?.isSuccess)
                textViewMessage.text =
                    if (CustomToast.toastData?.description.isNullOrEmpty()) {
                        "its look like you\\'re offline. Connect to the internet and try again"
                    } else {
                        CustomToast.toastData?.description
                    }
                constraintLayoutToast.setOnTouchListener(
                    object : OnSwipeTouchListener() {
                        override fun onSwipeTop() {
                            super.onSwipeTop()
                            dismissToast()
                        }
                    }
                )
                executePendingBindings()
            }
            rootView?.post {
                if (asDropDown) {
                    popupWindow.showAsDropDown(rootView, 0, 0)
                } else {
                    popupWindow.showAtLocation(rootView, Gravity.TOP, 0, 0)
                }
            }
            autoDismiss(3000)
        }
    }

    private fun setAnimationStyle() {
        animationStyle =
            when (gravity) {
                Gravity.TOP -> {
                    R.style.topAnimation
                }

                Gravity.BOTTOM -> {
                    R.style.bottomAnimation
                }

                else -> {
                    R.style.topAnimation
                }
            }
    }

    fun dismissToast() {
        try {
            popupWindow.dismiss()
        } catch (e: Exception) {
            Timber.e("--- Error: ${e.message}")
        } finally {
            showBanner = false
            asDropDown = false
        }
    }

    private fun autoDismiss(duration: Long) {
        if (duration > 0) {
            handler.removeCallbacksAndMessages(null)
            handler.postDelayed({ dismissToast() }, duration)
        }
    }

    open class OnSwipeTouchListener : OnTouchListener {
        private val gestureDetector by lazy {
            GestureDetector(activity, GestureListener())
        }

        override fun onTouch(
            v: View?,
            event: MotionEvent?
        ): Boolean {
            return gestureDetector.onTouchEvent(event!!)
        }

        inner class GestureListener : GestureDetector.SimpleOnGestureListener() {
            private val SWIPE_THRESHOLD = 1
            private val SWIPE_VELOCITY_THRESHOLD = 1

            override fun onDown(e: MotionEvent): Boolean {
                return true
            }

//            override fun onScroll(
//                e1: MotionEvent,
//                e2: MotionEvent,
//                distanceX: Float,
//                distanceY: Float
//            ): Boolean {
//                var result = false
//                try {
//                    val diffY = e2.y - e1.y
//                    val diffX = e2.x - e1.x
//                    if (Math.abs(diffX) > Math.abs(diffY)) {
//                        if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(distanceY) > SWIPE_VELOCITY_THRESHOLD) {
//                            if (diffX > 0) {
//                                onSwipeRight()
//                            } else {
//                                onSwipeLeft()
//                            }
//                        }
//                        result = true
//                    } else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(distanceY) > SWIPE_VELOCITY_THRESHOLD) {
//                        if (diffY > 0) {
//                            onSwipeBottom()
//                        } else {
//                            onSwipeTop()
//                        }
//                    }
//                    result = true
//                } catch (exception: java.lang.Exception) {
//                    exception.printStackTrace()
//                }
//                return result
//            }
        }

        open fun onSwipeRight() {
            Timber.e("--- onSwipeRight")
        }

        open fun onSwipeLeft() {
            Timber.e("--- onSwipeLeft")
        }

        open fun onSwipeTop() {
            Timber.e("--- onSwipeTop")
        }

        open fun onSwipeBottom() {
            Timber.e("--- onSwipeBottom")
        }
    }
}
