package com.studio.common.ui.extensions

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.provider.Settings
import android.text.TextUtils
import timber.log.Timber

/**
 * Get enabled accessibility services
 * Pls compare check terminal
 *
 * adb shell settings get secure enabled_accessibility_services
 *
 * @return
 */
fun Context.getEnabledAccessibilityServices(): List<String> {
    val enabledServices =
        Settings.Secure.getString(
            this.contentResolver,
            Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES,
        )
    return if (!TextUtils.isEmpty(enabledServices)) {
        enabledServices.split(":").map { it.trim() }
    } else {
        emptyList()
    }
}

fun Context.getAppNameFromApplicationID(serviceName: String): String? {
    try {
        val packageName = serviceName.split("/")[0]
        val packageManager = this.packageManager
        val applicationInfo = packageManager.getApplicationInfo(packageName, 0)
        return packageManager.getApplicationLabel(applicationInfo).toString()
    } catch (e: PackageManager.NameNotFoundException) {
        Timber.e("--- Error: ${e.message}")
        return null
    }
}

fun Context.isSystemApp(packageName: String): Boolean =
    try {
        val mPackageName = packageName.split("/")[0]
        val appInfo = this.packageManager.getApplicationInfo(mPackageName, 0)
        (appInfo.flags and ApplicationInfo.FLAG_SYSTEM != 0)
    } catch (e: Exception) {
        Timber.e("--- Error: ${e.message}")
        false
    }