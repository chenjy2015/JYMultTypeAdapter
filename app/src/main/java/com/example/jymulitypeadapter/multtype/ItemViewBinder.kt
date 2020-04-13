package com.example.jymulitypeadapter.multtype

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class ItemViewBinder<T, VH : RecyclerView.ViewHolder> {

    @Suppress("PropertyName")
    internal var _adapter: MultiTypeAdapter? = null
    /**
     * Gets the associated [MultiTypeAdapter].
     * @since v2.3.4
     */
    val adapter: MultiTypeAdapter
        get() {
            if (_adapter == null) {
                throw IllegalStateException(
                    "This $this has not been attached to MultiTypeAdapter yet. " +
                            "You should not call the method before registering the binder."
                )
            }
            return _adapter!!
        }

    var adapterItems: List<Any>
        get() = adapter.items
        set(value) {
            adapter.items = value
        }


    abstract fun onCreateViewHolder(layoutInflater: LayoutInflater, parent: ViewGroup): VH

    abstract fun onBindViewHolder(holder: VH, item: T)

    private fun onBindViewHolder(holder: VH, item: T, payloads: List<Any>) {
        onBindViewHolder(holder, item)
    }

    open fun getItemId(item: T): Long = RecyclerView.NO_ID

    open fun onViewRecycled(holder: VH) {}

    open fun onFailedToRecycleView(holder: VH): Boolean = false

    open fun onViewAttachedToWindow(holder: VH) {}

    open fun onViewDetachedFromWindow(holder: VH) {}
}