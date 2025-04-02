package com.studio.common.ui.base

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.studio.common.ui.constant.isOpenDialog
import com.studio.common.ui.navigator.NavigatorImpl
import com.studio.common.ui.widget.CustomToast

abstract class BaseBindingActivity<VM : BaseViewModel, DB : ViewDataBinding> : BaseActivity() {
    private var mBinding: DB? = null
    protected val binding get() = mBinding!!

    protected abstract fun getViewModelBindingVariable(): Int

    protected abstract val viewModel: VM

    private val navigator: NavigatorImpl by lazy { NavigatorImpl() }

    override fun initView() {
        mBinding = DataBindingUtil.setContentView(this, layoutId)
        binding.apply {
            setVariable(getViewModelBindingVariable(), viewModel)
            navigator.setContext(this@BaseBindingActivity)
            viewModel.setNavigator(navigator)
        }
        viewModel.toastMessage.observeSingle(this) {
            if (!isOpenDialog) {
                CustomToast.make(
                    view = binding.root,
                    activity = this@BaseBindingActivity,
                    toastData = it
                ).show()
            }
        }
    }

    override fun onDestroy() {
        mBinding = null
        super.onDestroy()
    }
}
