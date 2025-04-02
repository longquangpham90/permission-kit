package com.studio.common.ui.model

import android.text.InputType
import androidx.annotation.Keep

@Keep
enum class TypeInput(val value: Int) {
    TEXT(InputType.TYPE_CLASS_TEXT),
    NONE(InputType.TYPE_NULL),
    PASSWORD(InputType.TYPE_TEXT_VARIATION_PASSWORD),
    EMAIL(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS),
    BANK_CODE(InputType.TYPE_CLASS_TEXT),
    TYPE_TEXT_FLAG_CAP_CHARACTERS(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS)
}
