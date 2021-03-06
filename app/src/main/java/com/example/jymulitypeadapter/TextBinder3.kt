package com.example.jymulitypeadapter

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.jymulitypeadapter.databinding.BinderText3Binding
import com.example.mulitypeadapter.BaseItemViewBinder
import com.example.mulitypeadapter.BaseViewHolder

class TextBinder3 : BaseItemViewBinder<TextInfo, BinderText3Binding>() {
    override fun getLayoutId(): Int {
        return R.layout.binder_text3
    }

    @SuppressLint("SetTextI18n")
    override fun bind(holder: BaseViewHolder<BinderText3Binding>, item: TextInfo) {
        holder.viewDataBinding.tvContent.text = "id : ${item.id} , name : ${item.content}"
        holder.viewDataBinding.root.setBackgroundColor(
            ContextCompat.getColor(
                holder.viewDataBinding.root.context,
                R.color.blue
            )
        )
        holder.viewDataBinding.root.setOnClickListener {
            Toast.makeText(holder.viewDataBinding.root.context, item.content, Toast.LENGTH_SHORT)
                .show()
        }
    }

}