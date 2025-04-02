package com.studio.common.ui.navigator

import android.content.Context
import androidx.navigation.fragment.findNavController
import com.studio.common.ui.base.BaseActivity

class NavigatorImpl : Navigator {

    private var activityContext: Context? = null
    private val _mActivity get() = (activityContext as? BaseActivity)
    private val fragment get() = _mActivity?.supportFragmentManager?.fragments?.firstOrNull()
    override val navController get() = fragment?.findNavController()

    override fun setContext(context: Context?) {
        this.activityContext = context
    }
}
