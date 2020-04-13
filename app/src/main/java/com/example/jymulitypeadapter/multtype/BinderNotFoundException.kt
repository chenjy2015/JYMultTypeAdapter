package com.example.jymulitypeadapter.multtype

import java.lang.RuntimeException

internal class BinderNotFoundException(clazz: Class<*>) :
    RuntimeException("Have you registered the ${clazz.name} type and its binder to the adapter or types?")