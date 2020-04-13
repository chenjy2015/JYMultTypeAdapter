package com.example.jymulitypeadapter.multtype

interface Types {

    val size: Int

    fun <T> register(type: Type<T>)

    fun unregister(clazz: Class<*>): Boolean

    fun <T> getType(position: Int): Type<T>

    fun firstIndexOf(clazz: Class<*>): Int

}