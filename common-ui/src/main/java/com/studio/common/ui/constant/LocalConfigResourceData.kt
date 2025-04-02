package com.studio.common.ui.constant

import androidx.annotation.Keep
import javax.inject.Inject
import javax.inject.Named

@Keep
class LocalConfigResourceData
    @Inject
    constructor(
        @Named("appName") val appName: String,
        @Named("versionCode") val versionCode: Int,
        @Named("versionName") val versionName: String,
        @Named("timeBuildApp") val timeBuildApp: String,
        @Named("deviceOS") val deviceOS: String,
        @Named("deviceModel") val deviceModel: String,
        @Named("applicationId") val applicationId: String
    )
