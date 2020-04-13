package com.example.jymulitypeadapter

import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.jymulitypeadapter.databinding.BinderImgBinding

class ImgBinder : BaseItemViewBinder<ImgInfo, BinderImgBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.binder_img
    }

    override fun bind(holder: BaseViewHolder<BinderImgBinding>, item: ImgInfo) {
        Glide.with(holder.viewDataBinding.root.context).load(item.url)
            .into(holder.viewDataBinding.ivLogo)
        holder.viewDataBinding.root.setOnClickListener {
            Toast.makeText(holder.viewDataBinding.root.context, item.url, Toast.LENGTH_SHORT).show()
        }
    }

}