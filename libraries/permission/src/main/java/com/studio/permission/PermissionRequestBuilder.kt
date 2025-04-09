package com.studio.permission

import android.os.SystemClock
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.Locale

class PermissionRequestBuilder(
    private val activity: ComponentActivity,
    private val permissions: Array<String>,
    private val language: String = Locale.getDefault().language
) {
    private var grantedCallback: (() -> Unit)? = null
    private var deniedCallback: ((List<String>) -> Unit)? = null
    private var permanentlyDeniedCallback: ((List<String>) -> Unit)? = null

    fun onGranted(callback: () -> Unit): PermissionRequestBuilder {
        grantedCallback = callback
        return this
    }

    fun onDenied(callback: (List<String>) -> Unit): PermissionRequestBuilder {
        deniedCallback = callback
        return this
    }

    fun onPermanentlyDenied(callback: (List<String>) -> Unit): PermissionRequestBuilder {
        permanentlyDeniedCallback = callback
        return this
    }

    fun launch() {
        val key = "PERMISSION_LAUNCHER_${SystemClock.uptimeMillis()}"

        val permissionLauncher =
            activity.activityResultRegistry.register(
                key,
                ActivityResultContracts.RequestMultiplePermissions()
            ) { result ->
                val denied = result.filterValues { !it }.keys.toList()
                val granted = result.filterValues { it }.keys.toList()

                if (denied.isEmpty()) {
                    grantedCallback?.invoke()
                } else {
                    val permanentlyDenied =
                        denied.filter {
                            !ActivityCompat.shouldShowRequestPermissionRationale(activity, it)
                        }

                    if (permanentlyDenied.isNotEmpty()) {
                        permanentlyDeniedCallback?.invoke(permanentlyDenied)
                    } else {
                        deniedCallback?.invoke(denied)
                    }
                }
            }

        CoroutineScope(Dispatchers.Main).launch {
            delay(300)
            permissionLauncher.unregister()
            Timber.e("--- Unregistered permission launcher for key: $key")
        }
        permissionLauncher.launch(permissions)
    }
}
