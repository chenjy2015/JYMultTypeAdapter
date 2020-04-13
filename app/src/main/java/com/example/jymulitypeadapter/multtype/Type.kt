package com.example.jymulitypeadapter.multtype

class Type<T>(
    val clazz: Class<T>,
    val binder: ItemViewBinder<T, *>,
    val linker: Linker<T>
)