package com.example.jymulitypeadapter.multtype

import kotlin.reflect.KClass

interface KotlinClassLinker<T> {
    fun index(position: Int, item: T): KClass<out ItemViewBinder<T, *>>
}