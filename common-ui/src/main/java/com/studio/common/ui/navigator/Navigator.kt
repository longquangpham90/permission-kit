package com.studio.common.ui.navigator

import android.content.Context
import androidx.navigation.NavController

interface Navigator {
    val navController: NavController?

    fun setContext(context: Context?)
}
