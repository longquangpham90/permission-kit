package com.studio.permission.kit.utils.timber

import android.util.Log
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.crashlytics.ktx.setCustomKeys
import com.google.firebase.ktx.Firebase
import timber.log.Timber

class CrashlyticsTree : Timber.Tree() {
    companion object {
        private const val CRASHLYTICS_KEY_PRIORITY = "priority"
        private const val CRASHLYTICS_KEY_TAG = "tag"
        private const val CRASHLYTICS_KEY_MESSAGE = "message"
    }

    override fun log(
        priority: Int,
        tag: String?,
        message: String,
        throwable: Throwable?
    ) {
        // Only send the error logs to Firebase Crashlytics
        if (priority == Log.ERROR) {
            val crashlytics = Firebase.crashlytics
            crashlytics.setCustomKeys {
                key(CRASHLYTICS_KEY_PRIORITY, priority)
                key(CRASHLYTICS_KEY_TAG, tag ?: "Tag")
                key(CRASHLYTICS_KEY_MESSAGE, message)
            }
            throwable?.let {
                Firebase.crashlytics.recordException(throwable)
            } ?: run {
                Firebase.crashlytics.log(message)
            }
        }
    }
}
