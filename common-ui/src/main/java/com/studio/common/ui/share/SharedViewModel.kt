package com.studio.common.ui.share

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel
    @Inject
    constructor(private val state: SavedStateHandle) : ViewModel()
