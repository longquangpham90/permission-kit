package com.studio.common.ui.base

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseAdapterDiffUtil<M>(diffCallback: DiffUtil.ItemCallback<M>) :
    ListAdapter<M, BaseBindingViewHolderDiff<M>>(diffCallback) {
    private var recyclerView: RecyclerView? = null
    private var onLoadMore: (() -> Unit)? = null
    var isLoadingMore = false

    init {
        setupLoadMore()
    }

    abstract fun contentViewHolder(parent: ViewGroup, viewType: Int): BaseBindingViewHolderDiff<M>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        contentViewHolder(parent, viewType)

    override fun onBindViewHolder(holder: BaseBindingViewHolderDiff<M>, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    fun configLoadMore(
        loadMoreCallback: () -> Unit,
        rv: RecyclerView
    ) {
        onLoadMore = loadMoreCallback
        recyclerView = rv
        setupLoadMore()
    }

    private fun setupLoadMore() {
        var layoutManager = recyclerView?.layoutManager as? LinearLayoutManager
        if (layoutManager == null) {
            layoutManager = recyclerView?.layoutManager as? GridLayoutManager
        }
        layoutManager ?: return
        recyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (dy <= 0 || isLoadingMore) {
                    return
                }

                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val pastVisibleItems = layoutManager.findFirstVisibleItemPosition()
                if (visibleItemCount + pastVisibleItems >= totalItemCount) {
                    isLoadingMore = true
                    onLoadMore?.invoke()
                }
            }
        })
    }

    open fun releaseResources() {
        onLoadMore = null
        recyclerView = null
        isLoadingMore = false
    }
}

abstract class BaseBindingViewHolderDiff<T>(val binding: ViewBinding) :
    RecyclerView.ViewHolder(binding.root) {
    abstract fun bind(item: T)
}
