package com.studio.common.ui.extensions

import android.content.Intent
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES
import android.os.Bundle
import android.os.Parcelable

inline fun <reified T : Parcelable> Bundle.getParcelableArrayListExt(key: String): ArrayList<T>? = when {
    SDK_INT >= VERSION_CODES.TIRAMISU -> getParcelableArrayList(key, T::class.java)
    else -> @Suppress("DEPRECATION")
    getParcelableArrayList(key)
}

inline fun <reified T : Parcelable> Intent.getParcelableArrayListExt(key: String): ArrayList<T>? = when {
    SDK_INT >= VERSION_CODES.TIRAMISU -> getParcelableArrayListExtra(key, T::class.java)
    else -> @Suppress("DEPRECATION")
    getParcelableArrayListExtra(key)
}

inline fun <reified T : Parcelable> Bundle.getParcelableExt(key: String): T? = when {
    SDK_INT >= VERSION_CODES.TIRAMISU -> getParcelable(key, T::class.java)
    else -> @Suppress("DEPRECATION")
    getParcelable(key)
}

inline fun <reified T : Parcelable> Intent.getParcelableExt(key: String): T? = when {
    SDK_INT >= VERSION_CODES.TIRAMISU -> getParcelableExtra(key, T::class.java)
    else -> @Suppress("DEPRECATION")
    getParcelableExtra(key)
}
