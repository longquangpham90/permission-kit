package com.studio.splash;

import android.Manifest;
import android.content.Context;

import com.studio.common.ui.base.BaseViewModel;
import com.studio.common.ui.constant.LocalConfigResourceData;
import com.studio.common.ui.utils.SingleLiveEvent;
import com.studio.permission.PermissionHelper;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import dagger.hilt.android.qualifiers.ApplicationContext;
import timber.log.Timber;

@HiltViewModel
public class SplashViewModel extends BaseViewModel {

    private final Context mContext;
    private SingleLiveEvent<String> titleApp;
    private SingleLiveEvent<PermissionHelper> mPermission;

    @Inject
    public SplashViewModel(@ApplicationContext Context context, LocalConfigResourceData config) {
        mContext = context;
        String versionApp = mContext.getString(R.string.version_app, config.getAppName(), config.getVersionName());
        getTitleApp().postValue(versionApp);
    }

    public SingleLiveEvent<String> getTitleApp() {
        if (titleApp == null) {
            titleApp = new SingleLiveEvent<>();
        }
        return titleApp;
    }

    public SingleLiveEvent<PermissionHelper> getPermission() {
        if (mPermission == null) {
            mPermission = new SingleLiveEvent<>();
        }
        return mPermission;
    }

    public void setPermission(PermissionHelper permission) {
        getPermission().postValue(permission);
    }

    public void onSinglePermission() {
        getPermission().getValue().requestPermissions(Manifest.permission.POST_NOTIFICATIONS, Locale.getDefault().getLanguage(), () -> {
            Timber.e("--- Grant Permission success");
        }, deniedList -> {
            Timber.e("--- Permission denied: " + deniedList);
        }, permanentlyDeniedList -> {
            Timber.e("--- Permission permanentlyDeniedList: " + permanentlyDeniedList);
        });
    }

    public void onDualPermission() {
        List<String> permissions = Arrays.asList(Manifest.permission.POST_NOTIFICATIONS, Manifest.permission.NEARBY_WIFI_DEVICES);
        getPermission().getValue().requestPermissions(permissions, Locale.getDefault().getLanguage(), () -> {
            Timber.e("--- Grant Permission success");
        }, deniedList -> {
            Timber.e("--- Permission denied: " + deniedList);
        }, permanentlyDeniedList -> {
            Timber.e("--- Permission permanentlyDeniedList: " + permanentlyDeniedList);
        });
    }

    public void onTriplePermission() {
        List<String> permissions = Arrays.asList(Manifest.permission.POST_NOTIFICATIONS, Manifest.permission.NEARBY_WIFI_DEVICES, Manifest.permission.CAMERA, Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS);
        getPermission().getValue().requestPermissions(permissions, Locale.getDefault().getLanguage(), () -> {
            Timber.e("--- Grant Permission success");
        });
    }
}
