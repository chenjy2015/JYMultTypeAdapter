package com.example.jymulitypeadapter.multtype

import kotlin.reflect.KClass

interface OneToManyEndpoint<T> {
    fun withLinker(linker: Linker<T>)

    fun withLinker(linker: (position: Int, item: T) -> Int) {
        withLinker(object : Linker<T> {
            override fun index(position: Int, item: T): Int {
                return linker(position, item)
            }
        })
    }

    fun withJavaClassLinker(javaClassLinker: JavaClassLinker<T>)

    fun withJavaClassLinker(classLinker: (position: Int, item: T) -> Class<ItemViewBinder<T, *>>) {
        withJavaClassLinker(object : JavaClassLinker<T> {
            override fun index(position: Int, item: T): Class<out ItemViewBinder<T, *>> {
                return classLinker(position, item)
            }
        })
    }

    fun withKotlinClassLinker(classLinker: KotlinClassLinker<T>) {
        withJavaClassLinker { position, item -> classLinker.index(position, item).java as Class<ItemViewBinder<T, *>> }
    }

    fun withKotlinClassLinker(classLinker: (position: Int, item: T) -> KClass<out ItemViewBinder<T, *>>) {
        withJavaClassLinker { position, item -> classLinker(position, item).java as Class<ItemViewBinder<T, *>> }
    }

}