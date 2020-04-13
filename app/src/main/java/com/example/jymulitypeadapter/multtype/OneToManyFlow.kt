package com.example.jymulitypeadapter.multtype

import androidx.annotation.CheckResult

interface OneToManyFlow<T>{

    @CheckResult
    fun to(vararg binders: ItemViewBinder<T, *>): OneToManyEndpoint<T>

}