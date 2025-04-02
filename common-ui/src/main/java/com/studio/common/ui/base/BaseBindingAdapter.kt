package com.studio.common.ui.base

import androidx.databinding.ViewDataBinding

abstract class BaseBindingAdapter<T> : BaseAdapter<T, BaseBindingViewHolder<ViewDataBinding>>() {
    abstract fun getLayoutRes(viewType: Int): Int
}

abstract class BaseBindingSpinnerAdapter<T> :
    BaseSpinnerAdapter<T, BaseBindingViewHolder<ViewDataBinding>>() {
    abstract fun getLayoutRes(viewType: Int): Int
}
