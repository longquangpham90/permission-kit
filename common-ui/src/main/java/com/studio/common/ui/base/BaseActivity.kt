@file:Suppress("DEPRECATION")

package com.studio.common.ui.base

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import timber.log.Timber
import kotlin.math.ceil

abstract class BaseActivity :
    AppCompatActivity(),
    BaseActivityListener {
    @get:LayoutRes
    abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        initView()
        initData(intent = intent, isNewIntent = false)
        initViewModel()
    }

    override fun onStart() {
        adjustDisplayDensity()
        adjustFontScale()
        super.onStart()
    }

    private fun adjustDisplayDensity() {
        val displayMetrics = resources.displayMetrics
        val snap = 80
        val exactDpi = (displayMetrics.xdpi + displayMetrics.ydpi) / 2
        val dpi = displayMetrics.densityDpi.toFloat()
        if (dpi - exactDpi > snap) {
            val targetDpi = (ceil((exactDpi / snap).toDouble()) * snap).toInt()
            val log = "Changing DPI from $dpi to $targetDpi"
            val config = resources.configuration
            displayMetrics.densityDpi = targetDpi
            config.densityDpi = targetDpi
            displayMetrics.setTo(displayMetrics)
            config.setTo(config)
            resources.updateConfiguration(config, displayMetrics)
        }
    }

    private fun adjustFontScale() {
        val configuration = resources.configuration
        val fontScale = configuration.fontScale
        val targetFontScale = 1f
        if (fontScale != targetFontScale) {
            val log =
                String.format("Changing font scale from %.2f to %.2f", fontScale, targetFontScale)
            Timber.e("--- Log: $log")
            configuration.fontScale = targetFontScale
            val metrics = resources.displayMetrics
            val wm = getSystemService(WINDOW_SERVICE) as WindowManager
            wm.defaultDisplay.getMetrics(metrics)
            metrics.scaledDensity = configuration.fontScale * metrics.density
            baseContext.resources.updateConfiguration(configuration, metrics)
        }
    }

    override fun onStop() {
        super.onStop()
        System.gc()
        Runtime.getRuntime().gc()
    }

    override fun onDestroy() {
        super.onDestroy()
        System.gc()
        Runtime.getRuntime().gc()
    }
}

interface BaseActivityListener {
    fun initData(
        intent: Intent?,
        isNewIntent: Boolean
    )

    fun initView()

    fun initViewModel()
}
