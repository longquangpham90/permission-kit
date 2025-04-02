package com.studio.permission.kit.ui

import android.content.Intent
import androidx.activity.viewModels
import com.demo.permission.kit.BR
import com.demo.permission.kit.R
import com.demo.permission.kit.databinding.ActivityMainBinding
import com.studio.common.ui.base.BaseBindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseBindingActivity<MainViewModel, ActivityMainBinding>() {
    override fun getViewModelBindingVariable(): Int = BR.viewModel

    override val viewModel by viewModels<MainViewModel>()

    override val layoutId: Int = R.layout.activity_main

    override fun initData(
        intent: Intent?,
        isNewIntent: Boolean
    ) {
        Unit
    }

    override fun initViewModel() {
        Unit
    }
}
