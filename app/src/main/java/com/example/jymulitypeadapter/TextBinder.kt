package com.example.jymulitypeadapter

import android.annotation.SuppressLint
import android.widget.Toast
import com.example.jymulitypeadapter.databinding.BinderTextBinding
import com.example.mulitypeadapter.BaseItemViewBinder
import com.example.mulitypeadapter.BaseViewHolder

class TextBinder : BaseItemViewBinder<TextInfo, BinderTextBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.binder_text
    }

    @SuppressLint("SetTextI18n")
    override fun bind(holder: BaseViewHolder<BinderTextBinding>, item: TextInfo) {
        holder.viewDataBinding.tvContent.text = "id : ${item.id} , name : ${item.content}"
        holder.viewDataBinding.root.setOnClickListener {
            Toast.makeText(holder.viewDataBinding.root.context, item.content, Toast.LENGTH_SHORT).show()
        }
    }

}