@file:Suppress("DEPRECATION")

package com.studio.common.ui.extensions

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.content.res.Configuration
import android.net.ConnectivityManager
import android.net.Uri
import android.net.wifi.WifiManager
import android.os.Build
import android.provider.Settings.Secure
import android.util.Base64
import androidx.fragment.app.FragmentActivity
import timber.log.Timber
import java.net.Inet4Address
import java.net.InetAddress
import java.net.NetworkInterface
import java.security.MessageDigest
import java.util.Collections

@SuppressLint("WrongConstant")
fun FragmentActivity.getInstalledApps(): List<ResolveInfo?> {
    val mainIntent = Intent(Intent.ACTION_MAIN, null)
    mainIntent.addCategory(Intent.CATEGORY_LAUNCHER)
    return packageManager.queryIntentActivities(mainIntent, ApplicationInfo.FLAG_INSTALLED)
}

fun isSystemPackage(resolveInfo: ResolveInfo): Boolean = resolveInfo.activityInfo.applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM != 0

fun getMarketStore(packageName: String): String = "market://details?id=$packageName"

fun FragmentActivity.getDetailsMarket(): String = "market://details?id=" + packageName

fun getWebStore(packageName: String): String = "https://play.google.com/store/apps/details?id=$packageName"

fun FragmentActivity.getDetails(): String = "https://play.google.com/store/apps/details?id=" + packageName

fun getProductionApp(publisher_name: String): String = "market://search?q=pub:$publisher_name"

fun getProductionWeb(publisherName: String): String = "http://play.google.com/store/search?q=pub:$publisherName"

fun getDevIDApp(publisherName: String): String = "market://dev?id=$publisherName"

fun getDevIDWeb(publisherName: String): String = "https://play.google.com/store/apps/dev?id=$publisherName"

fun FragmentActivity.onActionRateApp() {
    var intent: Intent? = null
    intent =
        try {
            Intent(Intent.ACTION_VIEW, Uri.parse(getDetailsMarket()))
        } catch (e: ActivityNotFoundException) {
            Intent(Intent.ACTION_VIEW, Uri.parse(getDetails()))
        } finally {
            intent?.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            this.startActivity(intent)
        }
}

fun FragmentActivity.onOpenAppCHPlay(packageName: String?) {
    packageName?.let {
        var intent: Intent? = null
        intent =
            try {
                Intent(Intent.ACTION_VIEW, Uri.parse(getMarketStore(it)))
            } catch (anfe: ActivityNotFoundException) {
                Intent(Intent.ACTION_VIEW, Uri.parse(getWebStore(it)))
            } finally {
                intent?.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                this.startActivity(intent)
            }
    }
}

fun FragmentActivity.startMarket(publisherName: String?) {
    publisherName?.let {
        var intent: Intent? = null
        intent =
            try {
                Intent(Intent.ACTION_VIEW, Uri.parse(getMarketStore(publisherName)))
            } catch (anfe: ActivityNotFoundException) {
                Intent(Intent.ACTION_VIEW, Uri.parse(getWebStore(publisherName)))
            } finally {
                intent?.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                this.startActivity(intent)
            }
    }
}

fun FragmentActivity.onActionMoreApp(publisherName: String?) {
    publisherName?.let {
        var intent: Intent? = null
        intent =
            try {
                Intent(Intent.ACTION_VIEW, Uri.parse(getProductionApp(publisherName)))
            } catch (anfe: ActivityNotFoundException) {
                Intent(Intent.ACTION_VIEW, Uri.parse(getProductionWeb(publisherName)))
            } finally {
                intent?.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                this.startActivity(intent)
            }
    }
}

fun FragmentActivity.onActionStoreDevID(devID: String?) {
    devID?.let {
        var intent: Intent? = null
        try {
            intent = Intent(Intent.ACTION_VIEW, Uri.parse(getDevIDApp(devID)))
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            this.startActivity(intent)
        } catch (anfe: ActivityNotFoundException) {
            intent = Intent(Intent.ACTION_VIEW, Uri.parse(getDevIDWeb(devID)))
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            this.startActivity(intent)
        }
    }
}

/**
 * Trả về mã Hashkey tương ứng với keystore cho ứng dụng
 *
 * @return
 */
fun FragmentActivity.getHashKey(): String? {
    var str: String? = null
    try {
        val signatures =
            packageManager
                .getPackageInfo(
                    packageName,
                    PackageManager.GET_SIGNATURES
                ).signatures
        signatures?.map { sig ->
            var localMessageDigest: MessageDigest
            MessageDigest
                .getInstance("SHA1")
                .also { localMessageDigest = it }
                .update(sig.toByteArray())
            str = String(Base64.encode(localMessageDigest.digest(), 0))
        }
    } catch (e: Exception) {
        Timber.e("Error: ${e.message}")
    }
    return str?.replace("\n", "")
}

/**
 * Trả về chuỗi SHA1 tương ứng với keystore cho ứng dụng
 *
 * @return
 */
fun FragmentActivity.getSHA1(): String? {
    var str: String? = null
    try {
        val signatures =
            packageManager
                .getPackageInfo(
                    packageName,
                    PackageManager.GET_SIGNATURES
                ).signatures
        signatures?.map { sig ->
            var localMessageDigest: MessageDigest
            MessageDigest
                .getInstance("SHA-1")
                .also { localMessageDigest = it }
                .update(sig.toByteArray())
            str = byte2HexFormatted(localMessageDigest.digest())
        }
    } catch (e: Exception) {
        Timber.e("Error: ${e.message}")
    }
    return str?.replace("\n", "")
}

/**
 * Trả về chuỗi SHA-256 tương ứng với keystore cho ứng dụng
 *
 * @return
 */
fun FragmentActivity.getSHA256(): String? {
    var str: String? = null
    try {
        val signatures =
            packageManager
                .getPackageInfo(
                    packageName,
                    PackageManager.GET_SIGNATURES
                ).signatures
        signatures?.map { sig ->
            var localMessageDigest: MessageDigest
            MessageDigest
                .getInstance("SHA-256")
                .also { localMessageDigest = it }
                .update(sig.toByteArray())
            str = byte2HexFormatted(localMessageDigest.digest())
        }
    } catch (e: Exception) {
        Timber.e("Error: ${e.message}")
    }
    return str?.replace("\n", "")
}

private fun byte2HexFormatted(arr: ByteArray): String {
    val localStringBuilder = java.lang.StringBuilder(arr.size * 2)
    for (i in arr.indices) {
        var str: String
        var j: Int
        if (Integer.toHexString(arr[i].toInt()).also { str = it }.length.also {
                j = it
            } == 1
        ) {
            str = "0$str"
        }
        if (j > 2) {
            str = str.substring(j - 2, j)
        }
        localStringBuilder.append(str.uppercase())
        if (i < arr.size - 1) {
            localStringBuilder.append(':')
        }
    }
    return localStringBuilder.toString()
}

/**
 * Trả về địa chỉ IP Address non-localhost từ giao diện mạng
 * NetworkInterface. Yêu cầu cấp quyền sử dụng
 * Manifest.permission#ACCESS_NETWORK_STATE cho ứng dụng.
 *
 * @param useIPv4
 * @return
 */
fun getIPAddress(useIPv4: Boolean): String {
    try {
        val interfaces: List<NetworkInterface> =
            Collections.list(NetworkInterface.getNetworkInterfaces())
        for (intf in interfaces) {
            val addrs: List<InetAddress> = Collections.list(intf.inetAddresses)
            for (addr in addrs) {
                if (!addr.isLoopbackAddress) {
                    val sAddr = addr.hostAddress.uppercase()
                    // 						boolean isIPv4 = InetAddressUtils.isIPv4Address(sAddr);
                    val isIPv4 = Inet4Address.getByName(sAddr) != null
                    if (useIPv4) {
                        if (isIPv4) return sAddr
                    } else {
                        if (!isIPv4) {
                            val delim = sAddr.indexOf('%')
                            return if (delim < 0) sAddr else sAddr.substring(0, delim)
                        }
                    }
                }
            }
        }
    } catch (ex: Exception) {
        Timber.e("--- Error: ${ex.message}")
    }
    return ""
}

/**
 * Trả về địa chỉ MAC của thiết bị CHÚ Ý: Yêu cầu API LEVEL 9 trở lên
 *
 * @param interfaceName - eth0, wlan0 or NULL=use first interface
 * @return Trả về một chuỗi chứa địa chỉ mac nếu tìm thấy. Ngược lại trả về
 * chuỗi trống nếu không tìm thấy
 */
fun getMACAddress(interfaceName: String?): String {
    try {
        val interfaces: List<NetworkInterface> =
            Collections.list(NetworkInterface.getNetworkInterfaces())
        for (intf in interfaces) {
            if (interfaceName != null) {
                if (!intf.name.equals(interfaceName, ignoreCase = true)) continue
            }
            val mac = intf.hardwareAddress ?: return ""
            val buf = StringBuilder()
            for (idx in mac.indices) buf.append(String.format("%02X:", mac[idx]))
            if (buf.length > 0) buf.deleteCharAt(buf.length - 1)
            return buf.toString()
        }
    } catch (e: Exception) {
        Timber.e("Error: ${e.message}")
    }
    return ""
}

/**
 * Hàm lấy địa chỉ MAC của wifi. Yêu cầu cấp quyền sử dụng
 * Manifest.permission#ACCESS_NETWORK_STATE cho ứng dụng.
 *
 * @return
 */
@SuppressLint("MissingPermission", "WifiManagerLeak")
fun FragmentActivity.getWifiMacAddress(): String? {
    val wifiManager = getSystemService(Context.WIFI_SERVICE) as WifiManager
    val wInfo = wifiManager.connectionInfo
    return wInfo.macAddress
}

/**
 * Kiểm tra điện thoại đã kết
 *
 *
 * nối mạng (Internet) hay chưa?
 *
 * @return
 */
@SuppressLint("MissingPermission")
fun FragmentActivity.isConnectedToInternet(): Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val wifiNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
    if (wifiNetwork != null && wifiNetwork.isConnected) {
        return true
    }
    val mobileNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
    if (mobileNetwork != null && mobileNetwork.isConnected) {
        return true
    }
    val activeNetwork = cm.activeNetworkInfo
    return activeNetwork != null && activeNetwork.isConnected
}

/**
 * Kiểm tra xem ứng dụng đã cấp quyền truy cập nào đó hay chưa? Nếu ứng dụng
 * được cấp quyền thì có thể lấy các thông tin hoặc sử dụng các thông tin đó
 * trên thiết bị của người dùng.
 *
 * @param context
 * @param permission
 * @return
 * @throws NullPointerException
 */
@Throws(NullPointerException::class)
fun FragmentActivity.checkPermission(permission: String?): Boolean {
    permission?.let {
        return packageManager.checkPermission(it, packageName) != PackageManager.PERMISSION_GRANTED
    } ?: run {
        return false
    }
}

/**
 * Kiểm tra một Activity có trong file manifest hay chưa?
 *
 * @param _class
 * @return
 */
@Throws(PackageManager.NameNotFoundException::class)
fun FragmentActivity.isExsistActivity(_class: Class<*>?): Boolean {
    _class?.let {
        return packageManager.getActivityInfo(
            ComponentName(this, it),
            PackageManager.GET_META_DATA
        ) != null
    } ?: run {
        return false
    }
}

/**
 * Kiểm tra một Service có trong file manifest hay chưa?
 *
 * @param _class
 * @return
 */
@Throws(PackageManager.NameNotFoundException::class)
fun FragmentActivity.isExsistServices(_class: Class<*>?): Boolean {
    _class?.let {
        return packageManager.getServiceInfo(
            ComponentName(this, _class),
            PackageManager.GET_META_DATA
        ) != null
    } ?: run {
        return false
    }
}

/**
 * Kiểm tra đang chạy máy ảo hay máy thật
 *
 * @return false là máy thật | true là máy ảo
 */
fun isRunningOnEmulator(): Boolean {
    val model = Build.MODEL
    Timber.d("model: $model")
    val product = Build.PRODUCT
    Timber.d("product: $product")
    var isEmulator = false
    if (product != null) {
        isEmulator = product == "sdk" || product.contains("_sdk") || product.contains("sdk_")
    }
    Timber.d("isEmulator: $isEmulator")
    return isEmulator
}

fun isTablet(context: Context): Boolean =
    (context.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK >= Configuration.SCREENLAYOUT_SIZE_LARGE)

/**
 * ANDROID_ID là một chuỗi số 64-bit (như là một chuỗi hex) được ngẫu nhiên
 * được tạo ra khi người dùng đầu tiên thiết lập các thiết bị và phải duy
 * trì liên tục cho các đời của thiết bị của người dùng. Các giá trị có thể
 * thay đổi nếu một thiết lập lại nhà máy được thực hiện trên các thiết bị.
 * Xem thêm thông tin trong lớp Secure#ANDROID_ID
 *
 * @param context
 * @return Trả về một chuỗi số 64-bit (như là một chuỗi hex). Trả về chuỗi
 * rỗng nếu ID thiết bị không có sẵn.
 */
fun Context.getAndroidID(): String {
    var str = ""
    try {
        if ((
                Secure.getString(contentResolver, Secure.ANDROID_ID).also {
                    str = it
                }
            ) != null
        ) {
            return str
        }
    } catch (e: Exception) {
        Timber.e("--- Error: ${e.message}")
    }
    return ""
}

fun getSerialNumber(): String? {
    var serialNumber: String? = ""
    try {
        val c = Class.forName("android.os.SystemProperties")
        val get = c.getMethod("get", String::class.java)
        serialNumber = get.invoke(c, "gsm.sn1") as String
        if (serialNumber == "") serialNumber = get.invoke(c, "ril.serialnumber") as String
        if (serialNumber == "") serialNumber = get.invoke(c, "ro.serialno") as String
        if (serialNumber == "") serialNumber = get.invoke(c, "sys.serialnumber") as String
        if (serialNumber == "") serialNumber = Build.SERIAL
        if (serialNumber == "") serialNumber = null
    } catch (e: java.lang.Exception) {
        e.printStackTrace()
        serialNumber = null
    }
    return serialNumber
}

fun getIPMac(): String {
    try {
        val all: List<NetworkInterface> = Collections.list(NetworkInterface.getNetworkInterfaces())
        for (nif in all) {
            if (!nif.name.equals("wlan0", ignoreCase = true)) continue
            val macBytes = nif.hardwareAddress ?: return ""
            val res1 = java.lang.StringBuilder()
            for (b in macBytes) {
                res1.append(Integer.toHexString(b.toInt() and 0xFF) + ":")
            }

            if (res1.length > 0) {
                res1.deleteCharAt(res1.length - 1)
            }
            return res1.toString()
        }
    } catch (e: java.lang.Exception) {
        Timber.e("--- Error: " + e.message)
    }
    return ""
}
