package com.studio.permission.kit

import android.app.Application
import com.demo.permission.kit.BuildConfig
import com.studio.permission.kit.utils.timber.CrashlyticsTree
import com.studio.permission.kit.utils.timber.MyDebugTree
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        setupTimber()
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            val logDebugTree = MyDebugTree(this, true)
            logDebugTree.deleteFileLogFolder(this)
            Timber.plant(logDebugTree)
        } else {
            Timber.plant(CrashlyticsTree())
        }
    }
}
