package com.studio.common.ui.navigator

import android.net.Uri
import androidx.annotation.IdRes
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions

sealed class NavigationCommand {
    data class To(val directions: NavDirections) : NavigationCommand()

    data class ParentTo(val directions: NavDirections) : NavigationCommand()

    data class BackTo(
        @IdRes val destinationId: Int,
        val inclusive: Boolean
    ) : NavigationCommand()

    data class DeepLinkTo(val uri: Uri, val navOptions: NavOptions? = null) : NavigationCommand()

    data class ParentDeepLinkTo(val uri: Uri, val navOptions: NavOptions? = null) :
        NavigationCommand()

    data class BackWithData(val key: String, val value: Any) : NavigationCommand()

    data class BackToWithData(
        @IdRes val destinationId: Int,
        val inclusive: Boolean,
        val key: String,
        val value: Any
    ) : NavigationCommand()

    object Back : NavigationCommand()

    object ToRoot : NavigationCommand()
}
