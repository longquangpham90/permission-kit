package com.studio.splash

import android.Manifest
import android.content.Context
import com.studio.common.ui.base.BaseViewModel
import com.studio.common.ui.constant.LocalConfigResourceData
import com.studio.common.ui.utils.SingleLiveEvent
import com.studio.permission.PermissionHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject
import timber.log.Timber
import java.util.Locale

@HiltViewModel
class SplashViewModel
    @Inject
    constructor(
        @ApplicationContext val context: Context,
        config: LocalConfigResourceData
    ) : BaseViewModel() {
        val titleApp by lazy { SingleLiveEvent<String>() }
        val mPermission by lazy { SingleLiveEvent<PermissionHelper>() }

        init {
            val versionApp =
                context.resources.getString(R.string.version_app, config.appName, config.versionName)
            titleApp.postValue(versionApp)
        }

        fun setPermission(permission: PermissionHelper) {
            mPermission.postValue(permission)
        }

        fun onSinglePermission() {
            mPermission.value?.apply {
                requestPermissions(
                    Manifest.permission.POST_NOTIFICATIONS,
                    language = Locale.getDefault().language,
                    onGranted = {
                        Timber.Forest.e("--- Grant Permission success")
                    },
                    onDenied = {
                        Timber.Forest.e("--- Grant Permission onDenied")
                    },
                    onPermanentlyDenied = {
                        Timber.Forest.e("--- Grant Permission onPermanentlyDenied")
                    }
                )
            }
        }

        fun onDualPermission() {
            mPermission.value?.apply {
                requestPermissions(
                    Manifest.permission.POST_NOTIFICATIONS,
                    Manifest.permission.NEARBY_WIFI_DEVICES,
                    language = Locale.getDefault().language,
                    onGranted = {
                        Timber.Forest.e("--- Grant Permission success")
                    },
                    onDenied = {
                        Timber.Forest.e("--- Grant Permission onDenied")
                    },
                    onPermanentlyDenied = {
                        Timber.Forest.e("--- Grant Permission onPermanentlyDenied")
                    }
                )
            }
        }

        fun onTriplePermission() {
            mPermission.value?.apply {
                requestPermissions(
                    Manifest.permission.POST_NOTIFICATIONS,
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_SMS,
                    Manifest.permission.RECEIVE_SMS,
                    language = Locale.getDefault().language,
                    onGranted = {
                        Timber.Forest.e("--- Grant Permission success")
                    }
                )
            }
        }
    }
