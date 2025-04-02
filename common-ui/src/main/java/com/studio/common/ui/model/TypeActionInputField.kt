package com.studio.common.ui.model

import android.view.inputmethod.EditorInfo
import androidx.annotation.Keep

@Keep
enum class TypeActionInputField(val value: Int) {
    ACTION_DONE(EditorInfo.IME_ACTION_DONE),
    ACTION_NEXT(EditorInfo.IME_ACTION_NEXT)
}
