package com.studio.common.ui.extensions

import timber.log.Timber
import java.util.Locale
import kotlin.time.Duration.Companion.milliseconds

const val DATE_FORMAT_DATE_TIME_FULL = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
const val DATE_FORMAT_DATE_TIME_FULL_UTC = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'"
const val DATE_FORMAT_DATE_TIME_FULL_EXTRA = "EEE MMM dd HH:mm:ss zzzz yyyy"
const val DATE_FORMAT_DATE_TIME_Z = "yyyy-MM-dd'T'HH:mm:ssZ"
const val DATE_FORMAT_DATE_TIME = "yyyy-MM-dd'T'HH:mm:ss"
const val DATE_FORMAT_DATE_TIME_FULL_NO_ZONE = "yyyy-MM-dd HH:mm:ss"
const val DATE_FORMAT_DATE_TIME_SOURCE = "MM/dd/yyyy hh:mm:ss a"
const val DATE_FORMAT_DATE_TIME_12HOUR = "d MMM yyyy, hh:mm a"
const val DATE_FORMAT_DATE_TIME_12HOUR_EXTRA = "d MMM yyyy hh:mm a"
const val DATE_FORMAT_DATE_TIME_24HOUR = "d MMM yyyy, HH:mm"
const val DATE_FORMAT_DATE_TIME_24HOUR_EXTRA = "d MMM yyyy HH:mm"
const val DATE_FORMAT_DATE = "dd MMM yyyy"
const val DATE_FORMAT_DAY_AND_SHORT_MONTH = "dd MMM"
const val DATE_FORMAT_SIMPLE = "d MMM yyyy"
const val DATE_FORMAT_FULL = "d MMMM yyyy"
const val DATE_FORMAT_FULL_MONTH_YEAR = "MMMM yyyy"
const val DATE_FORMAT_FULL_DAY_OF_WEEK_DATE = "EEEE d"
const val DATE_FORMAT_SORT_DAY_OF_WEEK_DATE = "EEE dd MMM"
const val DATE_FORMAT_FULL_MCA = "yyyy-MM-dd"
const val DATE_FORMAT_MCA_YEAR_MONTH = "yyyy-MM"
const val DATE_FORMAT_TIME_12HOUR = "hh:mm"
const val DATE_FORMAT_TIME_12HOUR_AM = "hh:mm:ss a"
const val DATE_FORMAT_TIME_12HOUR_EXTRA = "hh:mm a"
const val DATE_FORMAT_MONTH_YEAR = "MM.YYYY"
const val DATE_FORMAT_TIME_24HOUR = "HH:mm"
const val DATE_FORMAT_TIME_MINUTE = "mm:ss"
const val DATE_FORMAT_TIME_SECOND = "ss"
const val DATE_FORMAT_DAY_OF_WEEK = "EEEE"

object DateFormatConstants {
    fun formatDurationTime(durationSeconds: Long) =
        durationSeconds.milliseconds.toComponents { hours, minutes, seconds, _ ->
            Timber.d("--- Time: $minutes:$seconds")
            String.format(
                Locale.getDefault(),
                "%02d:%02d:%02d",
                hours,
                minutes,
                seconds
            )
        }
}
