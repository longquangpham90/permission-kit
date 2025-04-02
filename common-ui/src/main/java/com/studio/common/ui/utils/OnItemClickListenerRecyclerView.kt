package com.studio.common.ui.utils

import android.view.View

interface OnItemClickListenerRecyclerView {
    fun onClick(
        view: View?,
        position: Int
    )

    fun onLongClick(
        view: View?,
        position: Int
    )
}
