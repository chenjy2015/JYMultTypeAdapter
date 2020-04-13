package com.example.jymulitypeadapter.multtype

interface JavaClassLinker<T> {
    fun index(position: Int, item: T): Class<out ItemViewBinder<T, *>>
}