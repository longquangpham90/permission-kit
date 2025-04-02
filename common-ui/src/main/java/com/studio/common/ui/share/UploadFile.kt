package com.studio.common.ui.share

import com.google.gson.annotations.SerializedName

data class UploadFile(
    @SerializedName("id")
    val id: Long,
    @SerializedName("jobID")
    val jobID: String? = null,
    @SerializedName("media_id")
    val mediaId: String? = null,
    @SerializedName("path")
    val path: String? = null,
    @SerializedName("mine_type")
    val mineType: String,
    @SerializedName("is_add_watermark")
    val isAddWatermark: Boolean = false
)
