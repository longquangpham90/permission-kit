package com.studio.splash

import androidx.fragment.app.viewModels
import com.studio.common.ui.base.BaseBindingFragment
import com.studio.permission.PermissionHelper
import com.studio.splash.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseBindingFragment<SplashViewModel, FragmentSplashBinding>() {
    override val viewModel by viewModels<SplashViewModel>()

    override fun getLayoutId(): Int = R.layout.fragment_splash

    override fun getViewModelBindingVariable(): Int = BR.viewModel

    override fun initView() {
        Unit
    }

    override fun initViewModel() {
        viewModel.setPermission(PermissionHelper(requireActivity()))
    }
}
