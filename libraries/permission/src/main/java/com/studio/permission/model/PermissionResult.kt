package com.studio.permission.model

sealed class PermissionResult {
    object Granted : PermissionResult()

    data class Denied(
        val deniedPermissions: List<String>
    ) : PermissionResult()

    data class PermanentlyDenied(
        val deniedPermissions: List<String>
    ) : PermissionResult()
}
