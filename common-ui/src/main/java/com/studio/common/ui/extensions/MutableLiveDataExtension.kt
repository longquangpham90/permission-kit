package com.studio.common.ui.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.studio.common.ui.utils.SingleLiveEvent

fun <T> MutableLiveData<T>.asLiveData() = this as LiveData<T>

fun <T> SingleLiveEvent<T>.asLiveData() = this as LiveData<T>
