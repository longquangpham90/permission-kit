package com.studio.splash

import android.Manifest
import android.content.Context
import androidx.activity.ComponentActivity
import androidx.lifecycle.viewModelScope
import com.studio.common.ui.base.BaseViewModel
import com.studio.common.ui.constant.LocalConfigResourceData
import com.studio.common.ui.utils.SingleLiveEvent
import com.studio.permission.PermissionHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class SplashViewModel
    @Inject
    constructor(
        @ApplicationContext val context: Context,
        config: LocalConfigResourceData
    ) : BaseViewModel() {
        val titleApp by lazy { SingleLiveEvent<String>() }

        init {
            val versionApp =
                context.resources.getString(R.string.version_app, config.appName, config.versionName)
            titleApp.postValue(versionApp)
        }

        fun onSinglePermission(activity: ComponentActivity) {
            viewModelScope.launch {
                PermissionHelper(activity)
                    .requestPermissions(
                        Manifest.permission.POST_NOTIFICATIONS,
                        language = Locale.getDefault().language,
                        onGranted = {
                            Timber.e("--- Grant Permission success")
                        },
                        onDenied = {
                            Timber.e("--- Grant Permission onDenied")
                        },
                        onPermanentlyDenied = {
                            Timber.e("--- Grant Permission onPermanentlyDenied")
                        }
                    ).launchIn(this)
            }
        }

        fun onDualPermission(activity: ComponentActivity) {
            viewModelScope.launch {
                PermissionHelper(activity)
                    .requestPermissions(
                        Manifest.permission.POST_NOTIFICATIONS,
                        Manifest.permission.NEARBY_WIFI_DEVICES,
                        language = Locale.getDefault().language,
                        onGranted = {
                            Timber.e("--- Grant Permission success")
                        },
                        onDenied = {
                            Timber.e("--- Grant Permission onDenied")
                        },
                        onPermanentlyDenied = {
                            Timber.e("--- Grant Permission onPermanentlyDenied")
                        }
                    ).launchIn(this)
            }
        }

        fun onTriplePermission(activity: ComponentActivity) {
            viewModelScope.launch {
                PermissionHelper(activity)
                    .requestPermissions(
                        Manifest.permission.POST_NOTIFICATIONS,
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_SMS,
                        language = Locale.getDefault().language,
                        onGranted = {
                            Timber.e("--- Grant Permission success")
                        }
                    ).launchIn(this)
            }
        }
    }
