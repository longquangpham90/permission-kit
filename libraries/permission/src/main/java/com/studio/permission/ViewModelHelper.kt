package com.studio.permission

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

class ViewModelHelper {
    companion object {
        @JvmStatic
        inline fun <reified VM : ViewModel> getViewModel(owner: ViewModelStoreOwner): VM {
            return ViewModelProvider(owner).get(VM::class.java)
        }
    }
}
