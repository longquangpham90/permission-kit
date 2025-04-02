package com.studio.common.ui.extensions

import android.content.res.Configuration
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import timber.log.Timber

@BindingAdapter("toast_background")
fun ConstraintLayout.initBackground(isSuccess: Boolean?) {
    when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
        Configuration.UI_MODE_NIGHT_YES -> {
            Timber.i("--- Mode background: Night")
            when (isSuccess) {
                true -> {
                }

                else -> {
                }
            }
        }
        Configuration.UI_MODE_NIGHT_NO -> {
            Timber.i("--- Mode background: Light")
            when (isSuccess) {
                true -> {
                }

                else -> {
                }
            }
        }
        else -> {
            Timber.i("--- Mode background: Unknown")
            when (isSuccess) {
                true -> {
                }

                else -> {
                }
            }
        }
    }
}

@BindingAdapter("toast_style_message")
fun TextView.initToastStyle(isSuccess: Boolean?) {
    when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
        Configuration.UI_MODE_NIGHT_YES -> {
            Timber.i("--- Mode text: Night")
        }
        Configuration.UI_MODE_NIGHT_NO -> {
            Timber.i("--- Mode text: Light")
        }
        else -> {
            Timber.i("--- Mode text: Unknown")
        }
    }
}
