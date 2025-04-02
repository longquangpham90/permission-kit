package com.studio.common.ui.extensions

import android.content.Context
import java.text.DecimalFormat
import java.util.*
import java.util.concurrent.ThreadLocalRandom

val decimalFormat = DecimalFormat("00")

fun Int?.toBoolean(): Boolean {
    return this == 1
}

fun Boolean?.toInt(): Int {
    return if (this == true) 1 else 0
}

fun Int?.orZero(): Int = this ?: 0

fun Long?.orZero(): Long = this ?: 0L

fun Float?.orZero(): Float = this ?: 0.0f

fun Double?.orZero(): Double = this ?: 0.0

fun Double?.isNullOrZero(): Boolean = this == null || this == 0.0

fun Long.convertNumberLongToString(): String {
    return if (this < 1000) {
        "" + this
    } else if (this < 1000000) {
        convertSub(this) + "K"
    } else if (this < 1000000000) {
        convertSub(this / 1000) + "M"
    } else if (this < 1000000000000L) {
        convertSub(this / 1000000) + "B"
    } else if (this < 1000000000000000L) {
        convertSub(this / 1000000000) + "KB"
    } else {
        convertSub(this / 1000000000000L) + "MB"
    }
}

private fun convertSub(l: Long): String {
    return if (l > 100000) {
        (l / 1000).toString() + ""
    } else if (l > 10000) {
        if (l / 100 % 10 == 0L) {
            (l / 1000).toString() + ""
        } else {
            (l / 1000).toString() + "." + l / 100 % 10
        }
    } else {
        if (l / 10 % 100 == 0L) {
            (l / 1000).toString() + ""
        } else {
            if (l / 10 % 10 == 0L) {
                (l / 1000).toString() + "." + l / 100 % 10
            } else {
                (l / 1000).toString() + "." + l / 10 % 100
            }
        }
    }
}

fun Int.between(start: Int, end: Int): Boolean {
    return this in start..end
}

fun Long.formatMilliSecondsToTime(format: String): String {
    var millis = this
    var result = ""
    val hr = millis / 3600000
    millis %= 3600000
    val min = millis / 60000
    millis %= 60000
    val sec = millis / 1000
    result = when (format) {
        DATE_FORMAT_TIME_MINUTE -> {
            "${decimalFormat.format(min)}:${decimalFormat.format(sec)}"
        }

        DATE_FORMAT_TIME_SECOND -> {
            decimalFormat.format(sec)
        }

        else -> {
            "${decimalFormat.format(hr)}:${decimalFormat.format(min)}:${decimalFormat.format(sec)}"
        }
    }

    return result
}

private fun Random.nextInt(range: IntRange): Int {
    return range.first + nextInt(range.last + 1 - range.first)
}

fun Context.rand(range: IntRange): Int {
    return ThreadLocalRandom.current().nextInt(range)
}

fun Int.numberValueToString(): String {
    return if (this.orZero() >= 10) {
        "9+"
    } else if (this.orZero() > 1) {
        "$this"
    } else {
        "1"
    }
}

fun Float.isWhole() = this % 1 == 0f

fun Double.convertCurrency(currencySymbol: String?): String {
    val value = String.format("%.2f", this)
    currencySymbol?.let {
        return "$currencySymbol$value"
    } ?: run {
        return "\$$value"
    }
}
