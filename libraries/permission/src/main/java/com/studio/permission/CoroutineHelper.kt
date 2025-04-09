package com.studio.permission

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object CoroutineHelper {
    @JvmStatic
    fun launchOnMain(
        owner: LifecycleOwner,
        runnable: Runnable
    ) {
        owner.lifecycleScope.launch(Dispatchers.Main) {
            runnable.run()
        }
    }

    @JvmStatic
    fun launchOnIO(
        owner: LifecycleOwner,
        runnable: Runnable
    ) {
        owner.lifecycleScope.launch(Dispatchers.IO) {
            runnable.run()
        }
    }

    @JvmStatic
    fun launch(
        owner: LifecycleOwner,
        dispatcher: CoroutineDispatcher,
        runnable: Runnable
    ) {
        owner.lifecycleScope.launch(dispatcher) {
            runnable.run()
        }
    }

    @JvmStatic
    fun launchOnMain(
        viewModel: ViewModel,
        runnable: Runnable
    ) {
        viewModel.viewModelScope.launch(Dispatchers.Main) {
            runnable.run()
        }
    }

    @JvmStatic
    fun launchOnIO(
        viewModel: ViewModel,
        runnable: Runnable
    ) {
        viewModel.viewModelScope.launch(Dispatchers.IO) {
            runnable.run()
        }
    }

    @JvmStatic
    fun launch(
        viewModel: ViewModel,
        dispatcher: CoroutineDispatcher,
        runnable: Runnable
    ) {
        viewModel.viewModelScope.launch(dispatcher) {
            runnable.run()
        }
    }
}
