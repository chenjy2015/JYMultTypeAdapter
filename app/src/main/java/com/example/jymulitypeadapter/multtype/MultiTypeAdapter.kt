package com.example.jymulitypeadapter.multtype

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.CheckResult
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import kotlin.reflect.KClass

class MultiTypeAdapter @JvmOverloads constructor(
    var items: List<Any> = emptyList(),
    val initialCapacity: Int = 0,
    val types: Types = MutableTypes(
        initialCapacity
    )
) : RecyclerView.Adapter<ViewHolder>() {


    fun <T> register(clazz: Class<T>, binder: ItemViewBinder<T, *>) {
        unregisterAllTypesIfNeeded(clazz)
        register(Type(clazz, binder, DefaultLinker()))
    }

    inline fun <reified T> register(binder: ItemViewBinder<T, *>) {
        register(T::class.java, binder)
    }

    fun <T : Any> register(clazz: KClass<T>, binder: ItemViewBinder<T, *>) {
        register(clazz.java, binder)
    }

    internal fun <T> register(type: Type<T>) {
        types.register(type)
        type.binder._adapter = this
    }

    @CheckResult
    fun <T> register(clazz: Class<T>): OneToManyFlow<T> {
        unregisterAllTypesIfNeeded(clazz)
        return OneToManyBuilder(this, clazz)
    }

    @CheckResult
    fun <T : Any> register(clazz: KClass<T>): OneToManyFlow<T> {
        return register(clazz.java)
    }

    fun registerAll(types: Types) {
        val size = types.size
        for (i in 0 until size) {
            val type = types.getType<Any>(i)
            unregisterAllTypesIfNeeded(type.clazz)
            register(type)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binder = types.getType<Any>(viewType).binder
        return binder.onCreateViewHolder(layoutInflater, parent)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        onBindViewHolder(holder, position, emptyList())
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int,
        payloads: List<Any>
    ) {
        getOutBinderByViewHolder(holder).onBindViewHolder(holder, items[position])
    }


    override fun getItemViewType(position: Int): Int {
        return indexInTypesOf(position, items[position])
    }

    override fun getItemId(position: Int): Long {
        val item = items[position]
        return types.getType<Any>(getItemViewType(position)).binder.getItemId(item)
    }

    override fun onViewRecycled(holder: ViewHolder) {
        getOutBinderByViewHolder(holder).onViewRecycled(holder)
    }

    override fun onFailedToRecycleView(holder: ViewHolder): Boolean {
        return getOutBinderByViewHolder(holder).onFailedToRecycleView(holder)
    }

    override fun onViewAttachedToWindow(holder: ViewHolder) {
        getOutBinderByViewHolder(holder).onViewAttachedToWindow(holder)
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        getOutBinderByViewHolder(holder).onViewDetachedFromWindow(holder)
    }


    @Suppress("UNCHECKED_CAST")
    private fun getOutBinderByViewHolder(holder: ViewHolder): ItemViewBinder<Any, ViewHolder> {
        return types.getType<Any>(holder.itemViewType).binder as ItemViewBinder<Any, ViewHolder>
    }


    private fun indexInTypesOf(position: Int, item: Any): Int {
        val index = types.firstIndexOf(item.javaClass)
        if (index != -1) {
            val linker = types.getType<Any>(index).linker
//            return index + linker.index(position, item)
            return linker.index(position, item)
        }
        throw BinderNotFoundException(item.javaClass)
    }

    private fun unregisterAllTypesIfNeeded(clazz: Class<*>) {
        if (types.unregister(clazz)) {
            Log.w(TAG, "The type ${clazz.simpleName} you originally registered is now overwritten.")
        }
    }

    companion object {
        private const val TAG = "MultiTypeAdapter"
    }
}