package com.studio.permission.extensions

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.net.Uri
import android.provider.Settings
import com.studio.permission.PermissionHelper.Companion.DEFAULT_LANGUAGE
import java.util.*

fun Context.openAppSettings() {
    val intent =
        Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            data = Uri.parse("package:$packageName")
        }
    startActivity(intent)
}

fun Context.getDeviceLanguage(): String = resources.configuration.locales[0].language

fun Context.getMetaDataValue(): String {
    val defaultValue = getDeviceLanguage()
    return try {
        val appInfo =
            packageManager.getApplicationInfo(
                packageName,
                PackageManager.GET_META_DATA
            )
        appInfo.metaData?.getString(DEFAULT_LANGUAGE, defaultValue) ?: defaultValue
    } catch (e: Exception) {
        defaultValue
    }
}

fun Context.wrapLanguage(language: String): Context {
    val locale = Locale(language)
    Locale.setDefault(locale)
    val config = Configuration(resources.configuration)
    config.setLocale(locale)
    return createConfigurationContext(config)
}
