package com.studio.common.ui.extensions

import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

fun TabLayout.setupWithViewPager(viewPager: ViewPager2?, labels: List<String>) {
    if (labels.size != viewPager?.adapter?.itemCount) {
        throw Exception("The size of list and the tab count should be equal!")
    }
    TabLayoutMediator(this, viewPager) { tab, position ->
        tab.text = labels[position]
    }.attach()
}

fun ViewPager2.clearDecorations() {
    if (itemDecorationCount > 0) {
        for (i in itemDecorationCount - 1 downTo 0) {
            removeItemDecorationAt(i)
        }
    }
}
