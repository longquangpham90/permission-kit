package com.studio.common.ui.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.os.Debug
import android.provider.Settings
import android.util.Base64
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.tasks.Task
import com.google.android.play.core.integrity.IntegrityManagerFactory
import com.google.android.play.core.integrity.StandardIntegrityManager.PrepareIntegrityTokenRequest
import com.google.android.play.core.integrity.StandardIntegrityManager.StandardIntegrityToken
import com.google.android.play.core.integrity.StandardIntegrityManager.StandardIntegrityTokenProvider
import com.google.android.play.core.integrity.StandardIntegrityManager.StandardIntegrityTokenRequest
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader

fun checkFrida(unit: () -> Unit) {
    val process: Process = Runtime.getRuntime().exec("ps")
    val reader: BufferedReader = BufferedReader(InputStreamReader(process.inputStream))
    var line: String
    while ((reader.readLine().also { line = it }) != null) {
        if (line.contains("frida")) {
            unit.invoke()
        }
    }
    val files = File("/proc/self/maps").list()
    files?.map { path ->
        if (path.contains("frida") || path.contains("gadget")) {
            unit.invoke()
        }
    }
    val file = File("/data/local/tmp/frida-server")
    if (file.exists()) {
        unit.invoke()
    }
}

fun isDeviceRooted(): Boolean {
    val paths =
        arrayOf(
            "/system/app/Superuser.apk",
            "/system/xbin/su",
            "/system/bin/su"
        )
    for (path in paths) {
        if (File(path).exists()) {
            return true
        }
    }
    return false
}

fun Context.checkSignature(unit: () -> Unit) {
    val signatures =
        packageManager
            .getPackageInfo(
                packageName,
                PackageManager.GET_SIGNATURES
            ).signatures
    signatures?.map { signature ->
        val currentSignature: String = signature.toCharsString()
        if (currentSignature.equals("")) {
            unit.invoke()
        }
    }
}

fun isProxySet(): Boolean {
    val proxyHost = System.getProperty("http.proxyHost")
    val proxyPort = System.getProperty("http.proxyPort")
    return !proxyHost.isNullOrEmpty() && !proxyPort.isNullOrEmpty()
}

private fun generateNonce(): String {
    val nonceBytes = ByteArray(16)
    java.util.Random().nextBytes(nonceBytes)
    return Base64.encodeToString(nonceBytes, Base64.NO_WRAP or Base64.URL_SAFE)
}

/**
 * Request integrity token standard
 *
 * @param context
 * @param cloudProjectNumber
 * @param listener
 * @see <a href="https://developer.android.com/google/play/integrity/overview">OverView</a>
 * @see <a href="https://developer.android.com/google/play/integrity/standard?#decrypt-and">Test Verify</a>
 */
fun requestIntegrityTokenStandard(
    context: Context,
    cloudProjectNumber: Long,
    listener: Listener
) {
    val standardIntegrityManager = IntegrityManagerFactory.createStandard(context)
    var integrityTokenProvider: StandardIntegrityTokenProvider? = null
    listener.onStart()
    standardIntegrityManager
        .prepareIntegrityToken(
            PrepareIntegrityTokenRequest.builder().setCloudProjectNumber(cloudProjectNumber).build()
        ).addOnSuccessListener { tokenProvider: StandardIntegrityTokenProvider ->
            integrityTokenProvider = tokenProvider
            val integrityTokenResponse: Task<StandardIntegrityToken> =
                integrityTokenProvider!!.request(
                    StandardIntegrityTokenRequest.builder().setRequestHash(generateNonce()).build()
                )
            integrityTokenResponse
                .addOnSuccessListener { response: StandardIntegrityToken ->
                    listener.onSuccess(response.token())
                    listener.onCompleted()
                }.addOnFailureListener { exception: Exception? ->
                    listener.onFail(Exception(exception))
                    listener.onCompleted()
                }
        }.addOnFailureListener { exception: Exception? ->
            listener.onFail(Exception(exception))
            listener.onCompleted()
        }
}

interface Listener {
    fun onStart()

    fun onSuccess(token: String)

    fun onFail(exception: Exception)

    fun onCompleted()
}

fun isDeveloperOptionsEnabled(context: Context): Boolean =
    Settings.Global.getInt(
        context.contentResolver,
        Settings.Global.DEVELOPMENT_SETTINGS_ENABLED,
        0
    ) == 1

@SuppressLint("MissingPermission")
fun isWiFiConnected(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = connectivityManager.activeNetworkInfo
    return activeNetwork != null && activeNetwork.type == ConnectivityManager.TYPE_WIFI
}

fun isDebuggerConnectedOrWaiting(): Boolean = Debug.isDebuggerConnected() || Debug.waitingForDebugger()

fun isRunningInWiFiDebug(context: Context): Boolean = isDebuggerConnectedOrWaiting() && isWiFiConnected(context)

fun isUsbDebuggingEnabled(context: Context): Boolean = Settings.Global.getInt(context.contentResolver, Settings.Global.ADB_ENABLED, 0) == 1

fun isGooglePlayServicesAvailable(context: Context): Boolean {
    val googleApiAvailability = GoogleApiAvailability.getInstance()
    val resultCode = googleApiAvailability.isGooglePlayServicesAvailable(context)
    return resultCode == ConnectionResult.SUCCESS
}
