@file:Suppress("DEPRECATION")

package com.studio.common.ui.extensions

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.provider.MediaStore
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.android.material.snackbar.Snackbar
import com.studio.common.ui.constant.APP_CHPLAY
import com.zackratos.ultimatebarx.ultimatebarx.statusBarOnly
import timber.log.Timber
import java.io.InputStream
import java.nio.charset.StandardCharsets

fun Fragment.statusBar(
    color: Int,
    isCustom: Boolean? = false,
    fitSystemWindows: Boolean? = true
) {
    val isLight =
        when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES -> {
                false
            }

            Configuration.UI_MODE_NIGHT_NO -> {
                true
            }

            else -> {
                false
            }
        }
    this.apply {
        statusBarOnly {
            fitWindow = fitSystemWindows ?: false
            colorRes = color
            lvlColor = color
            light = if (isCustom == true) false else isLight
        }
    }
}

fun Activity.hideKeyboard() {
    val imm: InputMethodManager? = getSystemService()
    currentFocus?.run {
        imm?.hideSoftInputFromWindow(this.windowToken, 0)
    }
}

fun Activity.showKeyboard(view: View? = currentFocus) {
    val imm: InputMethodManager? = getSystemService()
    view?.run {
        requestFocus()
        imm?.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }
}

fun Fragment.hideKeyboard() = activity?.hideKeyboard()

fun Fragment.showKeyboard(view: View? = null) = activity?.showKeyboard(view)

fun Activity.showSnack(
    view: View,
    message: String
) {
    Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
}

fun String.getMarketStore(): String = "market://details?id=$this"

fun Context.getDetailsMarket(): String = "market://details?id=" + this.packageName

fun String.getWebStore(): String = "https://play.google.com/store/apps/details?id=$this"

fun Context.getDetails(): String = "https://play.google.com/store/apps/details?id=" + this.packageName

fun String.getProductionApp(): String = "market://search?q=pub:$this"

fun String.getProductionWeb(): String = "http://play.google.com/store/search?q=pub:$this"

fun String.getDevIDApp(): String = "market://dev?id=$this"

fun String.getDevIDWeb(): String = "https://play.google.com/store/apps/dev?id=$this"

fun FragmentActivity.runApp(appPackageName: String?) {
    val intent = this.packageManager.getLaunchIntentForPackage(appPackageName.orEmpty())
    if (intent != null) {
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        this.startActivity(intent)
    } else {
        this.onOpenAppCHPlay(appPackageName.orEmpty())
    }
}

fun Context.onActionRateApp() {
    this.apply {
        var intent: Intent? = null
        intent =
            try {
                Intent(Intent.ACTION_VIEW, Uri.parse(getDetailsMarket()))
            } catch (anfe: ActivityNotFoundException) {
                Intent(Intent.ACTION_VIEW, Uri.parse(getDetails()))
            } finally {
                intent?.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)
            }
    }
}

fun Context.onOpenAppGooglePlay(packageName: String?) {
    var intent: Intent? = null
    intent =
        try {
            Intent(Intent.ACTION_VIEW, Uri.parse(getMarketStore(packageName.orEmpty())))
        } catch (anfe: ActivityNotFoundException) {
            Intent(Intent.ACTION_VIEW, Uri.parse(getWebStore(packageName.orEmpty())))
        } finally {
            intent?.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            this.startActivity(intent)
        }
}

fun Context.openStoreApp(packageName: String?) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("$APP_CHPLAY${packageName.orEmpty()}"))
    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
    startActivity(intent)
}

fun Context.openWebView(url: String) {
    val intent =
        Intent(
            Intent.ACTION_VIEW,
            Uri.parse(url)
        )
    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
    startActivity(intent)
}

@SuppressLint("Range")
fun Context.getInfoBasicData(uri: Uri?): Pair<String, Long> {
    var name = ""
    var size = 0L
    uri?.let {
        val cursor = this.contentResolver.query(it, null, null, null, null)
        if (cursor != null && cursor.moveToFirst()) {
            name = cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DISPLAY_NAME))
            size = cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Media.SIZE))
            cursor.close()
        }
    }
    return Pair(name, size)
}

fun Context.getAssetsGson(fileName: String?): String? {
    fileName?.let {
        try {
            val inputStream: InputStream = this.assets.open(fileName)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            val data = String(buffer, StandardCharsets.UTF_8)
            return data
        } catch (e: Exception) {
            Timber.e("--- Error: ${e.message}")
            return null
        }
    } ?: kotlin.run {
        return null
    }
}
