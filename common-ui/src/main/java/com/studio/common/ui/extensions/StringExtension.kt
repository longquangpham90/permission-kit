@file:Suppress("DEPRECATION")

package com.studio.common.ui.extensions

import android.annotation.SuppressLint
import android.os.Build
import android.text.Html
import android.text.Spanned
import com.studio.common.ui.constant.EMPTY
import com.studio.common.ui.constant.PATTERN_NON_NUMBER
import com.studio.common.ui.constant.PHONE_PLUS
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.*

fun String.unmask() = this.replace(PATTERN_NON_NUMBER.toRegex(), "")

fun String.mask(maskString: String): String {
    val result = StringBuilder()
    var index = 0
    for (charString in maskString) {
        if (this.length > index) {
            if (charString == '#') {
                result.append(this[index])
                index += 1
            } else {
                result.append(charString)
            }
        } else {
            return result.toString()
        }
    }
    return result.toString()
}

fun String.toHtml(): Spanned? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
    } else {
        Html.fromHtml(this)
    }
}

@SuppressLint("DefaultLocale")
fun String.capitalizeWords(): String = split(" ").joinToString(" ") { it.lowercase().capitalize() }

fun String.checkRegex(regex: Regex): Boolean {
    return this.matches(regex)
}

fun String.removePhonePlus(): String {
    return this.replace(PHONE_PLUS, EMPTY)
}

fun Locale.toNumberFormatCurrency(): NumberFormat {
    val format: NumberFormat = NumberFormat.getCurrencyInstance(this)
    if (format is DecimalFormat) {
        val decimalFormat: DecimalFormat = format
        val decimalFormatSymbols: DecimalFormatSymbols = DecimalFormat().decimalFormatSymbols
        decimalFormatSymbols.currency = decimalFormat.currency
        decimalFormat.decimalFormatSymbols = decimalFormatSymbols
    }
    return format
}
