package com.studio.common.ui.base

import androidx.lifecycle.ViewModel
import com.studio.common.ui.extensions.asLiveData
import com.studio.common.ui.model.ToastData
import com.studio.common.ui.navigator.NavigationCommand
import com.studio.common.ui.navigator.Navigator
import com.studio.common.ui.utils.SingleLiveEvent
import timber.log.Timber

abstract class BaseViewModel : ViewModel() {
    private val mLoadingProgress by lazy { SingleLiveEvent<Boolean>() }
    val loadingProgress = mLoadingProgress.asLiveData()

    val toastMessage by lazy { SingleLiveEvent<ToastData>() }

    private var _navigator: Navigator? = null
    val navigateEvent by lazy { SingleLiveEvent<NavigationCommand>() }

    val navigator get() = _navigator

    fun setNavigator(navigator: Navigator) {
        _navigator = navigator
    }

    fun navigate(navigationCommand: NavigationCommand) {
        navigateEvent.value = navigationCommand
    }

    fun handleErrorAPI(throwable: Throwable) {
    }

    fun showProgress(isShow: Boolean) {
        mLoadingProgress.postValue(isShow)
    }

    fun navigateToBack() {
        Timber.e("--- navigation to Back")
        navigate(NavigationCommand.Back)
    }
}
