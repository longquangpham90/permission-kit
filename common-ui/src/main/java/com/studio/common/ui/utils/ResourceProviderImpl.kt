package com.studio.common.ui.utils

import android.content.Context
import android.content.res.Configuration
import com.studio.common.ui.annotations.ConfigurationContext
import java.util.Locale
import javax.inject.Inject

class ResourceProviderImpl @Inject constructor(
    @ConfigurationContext override val context: Context
) : ResourceProvider {
    override fun getString(resId: Int, vararg args: Any): String {
        return if (args.isNotEmpty()) {
            getLocalizedContext().resources.getString(resId, *args)
        } else {
            getLocalizedContext().resources.getString(resId)
        }
    }

    override fun getQuantityString(id: Int, quantity: Int, vararg args: Any): String {
        return getLocalizedContext().resources.getQuantityString(id, quantity, *args)
    }

    override fun getDoubleString(resId: Int, args: Double): String {
        return getLocalizedContext().resources.getString(resId).replace("%d", args.toString())
    }

    private fun getLocalizedContext() = context.resources.configuration.let {
        context.createConfigurationContext(
            Configuration(it).apply { setLocale(Locale.getDefault()) }
        )
    }
}
