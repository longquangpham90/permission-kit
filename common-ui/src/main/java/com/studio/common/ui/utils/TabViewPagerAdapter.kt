package com.studio.common.ui.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class TabViewPagerAdapter(
    val manager: FragmentManager,
    lifeCycle: Lifecycle
) : FragmentStateAdapter(manager, lifeCycle) {

    private lateinit var linkedHashMap: LinkedHashMap<Int, Fragment>

    fun init(linkedHashMap: LinkedHashMap<Int, Fragment>? = null) {
        linkedHashMap?.let {
            this@TabViewPagerAdapter.linkedHashMap = it
        }
    }

    override fun createFragment(position: Int): Fragment {
        return linkedHashMap.values.elementAt(position)
    }

    override fun getItemCount(): Int = linkedHashMap.size
}
