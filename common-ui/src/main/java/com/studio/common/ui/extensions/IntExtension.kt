package com.studio.common.ui.extensions

import android.content.res.Resources
import android.util.DisplayMetrics
import java.text.NumberFormat
import java.util.Locale

fun Int.dpToPx(displayMetrics: DisplayMetrics): Int = (this * displayMetrics.density).toInt()

fun Int.pxToDp(displayMetrics: DisplayMetrics): Int = (this / displayMetrics.density).toInt()

fun Int.pxToDp(): Int = (this / Resources.getSystem().displayMetrics.density).toInt()

fun Int.dpToPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()

fun Double.toFormatAmount(): String {
    val numberFormat = NumberFormat.getNumberInstance(Locale.US)
    return numberFormat.format(this)
}

fun Int.secondToDisplayTime(): String {
    val second = this
    val displaySecond = second % 60
    val displayMinute = (second / 60) % 60
    return String.format("$displayMinute:%02d", displaySecond)
}

fun Long.formatMilliSecondsToTime(): String {
    var millis = this
    var result = ""
    val hr = millis / 3600000
    millis %= 3600000
    val min = millis / 60000
    millis %= 60000
    val sec = millis / 1000
    result = "${decimalFormat.format(hr)}:${decimalFormat.format(min)}:${decimalFormat.format(sec)}"
    return result
}

fun dp2px(dpValue: Float): Int {
    val scale = Resources.getSystem().displayMetrics.density
    return (dpValue * scale + 0.5f).toInt()
}
