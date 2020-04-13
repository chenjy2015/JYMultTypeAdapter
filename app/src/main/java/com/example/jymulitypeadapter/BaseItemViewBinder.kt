package com.example.jymulitypeadapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.jymulitypeadapter.multtype.ItemViewBinder

/**
 *
 * @Author :  chenjiayou
 * @Dscription : binder
 * @Create by : 2019/10/22
 *
 */
abstract class BaseItemViewBinder<T : Any, VD : ViewDataBinding> : ItemViewBinder<T, BaseViewHolder<VD>>() {

    private lateinit var act: Activity

    private lateinit var viewDataBinding: VD

    abstract fun getLayoutId(): Int

    abstract fun bind(holder: BaseViewHolder<VD>, item: T)

    override fun onBindViewHolder(holder: BaseViewHolder<VD>, item: T) {
        if (holder.viewDataBinding.root.context is Activity) {
            act = holder.viewDataBinding.root.context as Activity
        }
        bind(holder, item)
    }

    @Suppress("UNCHECKED_CAST")
    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): BaseViewHolder<VD> {
        val convertView: View = inflater.inflate(getLayoutId(), parent, false)
        viewDataBinding = DataBindingUtil.bind(convertView)!!
        return BaseViewHolder(viewDataBinding)
    }
}