package com.studio.common.ui.base

import android.widget.BaseAdapter
import androidx.recyclerview.widget.RecyclerView
import com.studio.common.ui.utils.OnItemClickListenerRecyclerView

@Suppress("ktlint:standard:property-naming")
abstract class BaseSpinnerAdapter<T, VH : RecyclerView.ViewHolder> : BaseAdapter() {
    open val ITEM_EMPTY = -1
    open val ITEM_NORMAL = 0
    var onItemClickListener: OnItemClickListenerRecyclerView? = null

    fun compareDiffUtil(
        oldItem: T,
        newItem: T
    ): Boolean = oldItem == newItem

    var items = mutableListOf<T>()
    var originItems = mutableListOf<T>()

    override fun getItemId(position: Int): Long {
        val item = getItem(position)
        return item?.hashCode()?.toLong() ?: System.currentTimeMillis()
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item == null) ITEM_EMPTY else ITEM_NORMAL
    }

    /**
     * function support submit data to adapter
     *
     * @param list - data submit
     * @param isSupportFilter [Boolean.TRUE] if you want filterable adapter, default [Boolean.FALSE]
     */
    fun submitData(
        list: ArrayList<T>?,
        isSupportFilter: Boolean? = false
    ) {
        if (isSupportFilter == true) {
            originItems.clear()
            items.clear()
        }
        list?.let {
            if (items.isEmpty()) {
                originItems.addAll(it)
                items.addAll(it)
                notifyDataSetChanged()
            } else {
                items.clear()
                items.addAll(list)
            }
        }
    }

    fun clear() {
        originItems.clear()
        items.clear()
        notifyDataSetChanged()
    }
}
