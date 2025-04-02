package com.studio.common.ui.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class TabViewPagerExtAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    private lateinit var linkedHashMap: LinkedHashMap<Int, Fragment>

    fun init(linkedHashMap: LinkedHashMap<Int, Fragment>? = null) {
        linkedHashMap?.let {
            this@TabViewPagerExtAdapter.linkedHashMap = it
        }
    }

    override fun createFragment(position: Int): Fragment {
        return linkedHashMap.values.elementAt(position)
    }

    override fun getItemCount(): Int = linkedHashMap.size
}
