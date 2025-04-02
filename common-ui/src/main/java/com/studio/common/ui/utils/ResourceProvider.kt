package com.studio.common.ui.utils

import android.content.Context

interface ResourceProvider {
    val context: Context

    fun getString(resId: Int, vararg args: Any = emptyArray()): String

    fun getQuantityString(id: Int, quantity: Int, vararg args: Any): String
    fun getDoubleString(resId: Int, args: Double): String
}
