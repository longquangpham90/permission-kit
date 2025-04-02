package com.studio.permission.kit.di

import android.os.Build
import com.demo.permission.kit.BuildConfig
import com.studio.common.ui.constant.LocalConfigResourceData
import com.studio.common.ui.constant.PLATFORM_OS
import com.studio.common.ui.extensions.capitalizeWords
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providerConfigApp() =
        LocalConfigResourceData(
            appName = BuildConfig.APP_NAME,
            versionCode = BuildConfig.VERSION_CODE,
            versionName = BuildConfig.VERSION_NAME,
            timeBuildApp =
                BuildConfig.TIME_BUILD_APP,
            deviceOS = "$PLATFORM_OS - ${Build.VERSION.SDK_INT}".capitalizeWords(),
            deviceModel = "${Build.MANUFACTURER.capitalizeWords()} ${Build.MODEL}",
            applicationId = BuildConfig.APPLICATION_ID
        )

    @Provides
    @Singleton
    @Named("PROJECT_ID")
    fun providerProjectID() = BuildConfig.PROJECT_ID
}
