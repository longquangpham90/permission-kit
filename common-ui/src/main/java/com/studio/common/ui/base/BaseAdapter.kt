package com.studio.common.ui.base

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.studio.common.ui.utils.OnItemClickListenerRecyclerView

abstract class BaseAdapter<T, VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {
    open val ITEM_EMPTY = -1
    open val ITEM_NORMAL = 0
    var onItemClickListener: OnItemClickListenerRecyclerView? = null

    fun compareDiffUtil(oldItem: T, newItem: T): Boolean = oldItem == newItem

    var items = mutableListOf<T>()
    var originItems = mutableListOf<T>()

    override fun getItemId(position: Int): Long {
        val item = getItem(position)
        return item?.hashCode()?.toLong() ?: System.currentTimeMillis()
    }

    fun getItem(position: Int): T? {
        return if (position in 0 until itemCount) items[position] else null
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item == null) ITEM_EMPTY else ITEM_NORMAL
    }

    override fun getItemCount(): Int {
        return items.size
    }

    /**
     * function support submit data to adapter
     *
     * @param list - data submit
     * @param isSupportFilter [Boolean.TRUE] if you want filterable adapter, default [Boolean.FALSE]
     */
    fun submitData(list: ArrayList<T>?, isSupportFilter: Boolean? = false) {
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
                notifyWithDiffUtil(items, list) {
                    items.clear()
                    items.addAll(list)
                }
            }
        }
    }

    fun removeItem(data: T?) {
        val position = items.indexOf(data)
        items.remove(data)
        notifyItemRangeRemoved(position, 1)
    }

    fun removeItem(position: Int) {
        items.removeAt(position)
        notifyItemRangeChanged(position, itemCount - 1)
    }

    fun clear() {
        originItems.clear()
        items.clear()
        notifyDataSetChanged()
    }

    private fun notifyWithDiffUtil(oldItems: List<T>, newItems: List<T>, updateData: () -> Unit) {
        val diff = DiffUtil.calculateDiff(
            object : DiffUtil.Callback() {

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    return compareDiffUtil(
                        oldItems[oldItemPosition],
                        newItems[newItemPosition]
                    )
                }

                override fun areContentsTheSame(
                    oldItemPosition: Int,
                    newItemPosition: Int
                ): Boolean {
                    return oldItems[oldItemPosition]?.equals(newItems[newItemPosition]) ?: false
                }

                override fun getOldListSize() = oldItems.size

                override fun getNewListSize() = newItems.size
            },
            false
        )
        updateData()
        diff.dispatchUpdatesTo(this)
    }
}
