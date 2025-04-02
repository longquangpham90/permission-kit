package com.studio.common.ui.extensions

fun <T> List<T>.toArrayList(): ArrayList<T> {
    return ArrayList(this)
}

fun <T> List<T>.safeSubList(fromIndex: Int, toIndex: Int): List<T> =
    this.subList(fromIndex, toIndex.coerceAtMost(this.size))

fun <T> List<T>.safeTake(toIndex: Int): List<T> =
    safeSubList(0, toIndex.coerceAtMost(this.size))
