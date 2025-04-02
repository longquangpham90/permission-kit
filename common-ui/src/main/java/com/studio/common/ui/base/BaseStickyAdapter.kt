package com.studio.common.ui.base

import android.view.View
import androidx.core.view.ViewCompat
import com.jay.widget.StickyHeaders
import com.xwray.groupie.GroupieAdapter

class BaseStickyAdapter : GroupieAdapter(), StickyHeaders, StickyHeaders.ViewSetup {
    override fun isStickyHeader(position: Int): Boolean {
        val item = getItem(position)
        return item is StickyHeaders
    }

    override fun setupStickyHeaderView(stickyHeader: View?) {
        stickyHeader?.let { ViewCompat.setElevation(it, 10F) }
    }

    override fun teardownStickyHeaderView(stickyHeader: View?) {
        stickyHeader?.let { ViewCompat.setElevation(it, 0F) }
    }
}
