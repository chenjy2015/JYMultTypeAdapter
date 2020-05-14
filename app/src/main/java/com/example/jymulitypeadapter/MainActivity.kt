package com.example.jymulitypeadapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jymulitypeadapter.databinding.ActivityMainBinding
import com.example.mulitypeadapter.MultiTypeAdapter

class MainActivity : AppCompatActivity() {

    var data = arrayListOf<Any>()

    lateinit var dataBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        init()
        setAdapter()
    }

    private fun init() {
        val urls = arrayOf(
            "http://lz-saas-oss-test.oss-cn-shenzhen.aliyuncs.com/file/u/file-4822297527ef43df8b2fd27628949351.png",
            "http://lz-saas-oss-test.oss-cn-shenzhen.aliyuncs.com/file/p/file-cc8c81ae444041dbb634561a166e1da7.png",
            "http://lz-saas-oss-test.oss-cn-shenzhen.aliyuncs.com/file/l/file-46effe9db35044f6a00b4efa5bf59aa5.png",
            "http://lz-saas-oss-test.oss-cn-shenzhen.aliyuncs.com/file/b/file-0c44db2eb7e64f99b8b22ffb7ad4ecf1.png",
            "http://lz-saas-oss-test.oss-cn-shenzhen.aliyuncs.com/file/t/file-082df646ff134ccfbc0e5f9308a9f635.png",
            "http://lz-saas-oss-test.oss-cn-shenzhen.aliyuncs.com/file/e/file-6ad26b3650e84097bb5df0cf6dd95ed4.png",
            "http://lz-saas-oss-test.oss-cn-shenzhen.aliyuncs.com/file/2/file-b4a0c91aafcf4f05a0b6d39def2c078c.png",
            "http://lz-saas-oss-test.oss-cn-shenzhen.aliyuncs.com/file/j/file-ae1fab6e90984f5fba6161e48b37f820.png",
            "http://lz-saas-oss-test.oss-cn-shenzhen.aliyuncs.com/file/d/file-d6de74e6e2cb42158b106906d5a29b21.png",
            "http://lz-saas-oss-test.oss-cn-shenzhen.aliyuncs.com/file/s/file-a1023c4eedd84bdca78b642f6abfde6d.png"
        )
        val names = arrayOf(
            "领筑组织中心",
            "领筑企业控制台",
            "领筑账号中心",
            "领筑用户中心",
            "领筑消息中心",
            "领筑产品中心",
            "领筑合作中心",
            "领筑应用中心",
            "领筑服务中心",
            "领筑项目中心",
            "领筑组织中心",
            "领筑企业控制台",
            "领筑账号中心",
            "领筑用户中心",
            "领筑消息中心",
            "领筑产品中心",
            "领筑合作中心",
            "领筑应用中心",
            "领筑服务中心",
            "领筑项目中心"
        )
        val ids = arrayOf(
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
        )
        for ((index, id) in ids.withIndex()) {
//            data.add(ImgInfo(id, urls[index]))
            data.add(TextInfo(id, names[index]))
        }
    }

    private fun setAdapter() {
        val adapter = MultiTypeAdapter()
//        adapter.register(TextInfo::class.java, TextBinder())
//        adapter.register(ImgInfo::class.java, ImgBinder())
        adapter.items = data
        adapter.register(TextInfo::class.java).to(TextBinder(), TextBinder2(), TextBinder3())
            .withLinker { position, item ->
                return@withLinker when {
                    item.id % 2 == 0 -> 0
                    item.id % 3 == 0 -> 1
                    else -> 2
                }
            }
        dataBinding.recycler.adapter = adapter
        dataBinding.recycler.layoutManager = LinearLayoutManager(this)
    }
}
