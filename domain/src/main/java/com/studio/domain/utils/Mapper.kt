package com.studio.domain.utils

interface Mapper<D, E> {
    fun map(input: D): E
}
