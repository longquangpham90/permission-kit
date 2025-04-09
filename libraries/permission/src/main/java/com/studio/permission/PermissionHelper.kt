package com.studio.permission

import android.Manifest
import android.content.Context
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import com.studio.permission.extensions.getMetaDataValue
import com.studio.permission.extensions.openAppSettings
import com.studio.permission.extensions.wrapLanguage
import com.studio.permission.model.PermissionResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.Locale
import java.util.function.Consumer

/**
 * PermissionHelper - Utility class for handling runtime permissions in Android +13.
 *
 * ### Usage Example:
 * ```
 *class DemoActivity : AppCompatActivity() {
 *     private val permissionHelper by lazy { PermissionHelper(activity = this@DemoActivity) }
 *
 *     override fun onCreate(savedInstanceState: Bundle?) {
 *         super.onCreate(savedInstanceState)
 *             permissionHelper
 *                 .requestPermissions(
 *                     Manifest.permission.READ_SMS,
 *                     language = Locale.getDefault().language,
 *                     onGranted = { Timber.e("--- permission success") },
 *                     onDenied = { Timber.e("--- permission denied but rationale is true") },
 *                     onPermanentlyDenied = { Timber.e("--- permission permanently denied") }
 *                 )
 *     }
 * }
 * ```
 *
 * @property activity The registry for handling activity results.
 */
class PermissionHelper
    @JvmOverloads
    constructor(
        private val activity: ComponentActivity
    ) {
        private var _lang: String? = null

        var lang: String
            get() = _lang ?: activity.getMetaDataValue()
            set(value) {
                _lang = value
            }

        companion object {
            const val REQUEST_PERMISSIONS = "request_permissions"
            const val DEFAULT_LANGUAGE = "default_language"
        }

        @JvmOverloads
        fun requestPermissions(
            permissions: String,
            language: String = Locale.getDefault().language,
            onGranted: Runnable? = null,
            onDenied: Consumer<List<String>>? = null,
            onPermanentlyDenied: Consumer<List<String>>? = null
        ) {
            PermissionHelper(activity)
                .request(
                    permissions,
                    language = language,
                    onGranted = onGranted,
                    onDenied = onDenied,
                    onPermanentlyDenied = onPermanentlyDenied
                ).launchIn(activity.lifecycleScope)
        }

        @JvmOverloads
        fun requestPermissions(
            permissions: List<String>,
            language: String = Locale.getDefault().language,
            onGranted: Runnable? = null,
            onDenied: Consumer<List<String>>? = null,
            onPermanentlyDenied: Consumer<List<String>>? = null
        ) {
            PermissionHelper(activity)
                .request(
                    *permissions.toTypedArray(),
                    language = language,
                    onGranted = onGranted,
                    onDenied = onDenied,
                    onPermanentlyDenied = onPermanentlyDenied
                ).launchIn(activity.lifecycleScope)
        }

        fun requestPermissions(
            vararg permissions: String,
            language: String = Locale.getDefault().language,
            onGranted: Runnable? = null,
            onDenied: Consumer<List<String>>? = null,
            onPermanentlyDenied: Consumer<List<String>>? = null
        ) {
            PermissionHelper(activity)
                .request(
                    *permissions,
                    language = language,
                    onGranted = onGranted,
                    onDenied = onDenied,
                    onPermanentlyDenied = onPermanentlyDenied
                ).launchIn(activity.lifecycleScope)
        }

        private fun request(
            vararg permissions: String,
            language: String? = Locale.getDefault().language,
            onGranted: Runnable? = null,
            onDenied: Consumer<List<String>>? = null,
            onPermanentlyDenied: Consumer<List<String>>? = null
        ): Flow<PermissionResult> {
            val requestPermissions = permissions.toList()
            lang = language.toString()
            return callbackFlow {
                val permissionLauncher =
                    activity.activityResultRegistry.register(
                        "${REQUEST_PERMISSIONS}_${permissions.joinToString()}",
                        ActivityResultContracts.RequestMultiplePermissions()
                    ) { results ->
                        if (results.isNotEmpty()) {
                            Timber.e("--- permission results: $results")
                            val denied = results.filterValues { !it }.keys.toList()
                            val allGranted = denied.isEmpty()
                            if (allGranted) {
                                trySend(PermissionResult.Granted)
                                onGranted?.run()
                            } else {
                                val permanentlyDenied =
                                    denied.filter {
                                        !ActivityCompat.shouldShowRequestPermissionRationale(activity, it)
                                    }

                                if (permanentlyDenied.isNotEmpty()) {
                                    Timber.e("--- permission permanently denied")
                                    trySend(PermissionResult.PermanentlyDenied(permanentlyDenied))
                                    onPermanentlyDenied?.accept(permanentlyDenied)
                                        ?: showPermissionSettingsDialog(activity, permanentlyDenied)
                                } else {
                                    Timber.e("--- permission denied but rationale is true")
                                    trySend(PermissionResult.Denied(denied))
                                    onDenied?.accept(denied)
                                }
                            }
                        }
                        close()
                    }
                Timber.e("--- permission request: ${permissions.toList()}")
                permissionLauncher.launch(requestPermissions.toTypedArray())
                awaitClose {
                    CoroutineScope(Dispatchers.Main).launch {
                        delay(250)
                        permissionLauncher.unregister()
                        Timber.e("--- permission unregistered")
                    }
                }
            }
        }

        private fun showPermissionSettingsDialog(
            context: Context,
            deniedPermissions: List<String>
        ) {
            if (deniedPermissions.isEmpty()) return
            val localizedContext = context.wrapLanguage(lang)
            val message =
                buildString {
                    append(localizedContext.getString(R.string.grant_permission_need_to_access))
                    deniedPermissions.forEach { permission ->
                        append(
                            "• ${
                                mapPermissionToName(
                                    context = localizedContext,
                                    permission = permission
                                )
                            }\n"
                        )
                    }
                    append(localizedContext.getString(R.string.grant_permission_please_navigate_to_setting))
                }
            AlertDialog
                .Builder(context)
                .setTitle(localizedContext.getString(R.string.grant_permission_access_denied))
                .setMessage(message)
                .setPositiveButton(localizedContext.getString(R.string.grant_permission_open_setting)) { _, _ ->
                    context.openAppSettings()
                }.setNegativeButton(localizedContext.getString(R.string.grant_permission_cancel), null)
                .show()
        }

        private fun mapPermissionToName(
            context: Context,
            permission: String
        ): String =
            when (permission) {
                Manifest.permission.CAMERA -> {
                    context.getString(R.string.grant_permission_camera_access)
                }

                Manifest.permission.READ_EXTERNAL_STORAGE -> {
                    context.getString(R.string.grant_permission_read_external_storage)
                }

                Manifest.permission.WRITE_EXTERNAL_STORAGE -> {
                    context.getString(R.string.grant_permission_write_external_storage)
                }

                Manifest.permission.ACCESS_FINE_LOCATION -> {
                    context.getString(R.string.grant_permission_access_fine_location)
                }

                Manifest.permission.RECORD_AUDIO -> {
                    context.getString(R.string.grant_permission_record_audio)
                }

                else -> permission
            }
    }
