package com.studio.common.ui.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class ToastData(
    @SerializedName("startDrawable") val startDrawable: Int? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("isSuccess") val isSuccess: Boolean? = false
) : Parcelable
