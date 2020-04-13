package com.example.jymulitypeadapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jymulitypeadapter.databinding.ActivityMainBinding
import com.example.jymulitypeadapter.multtype.MultiTypeAdapter
import org.w3c.dom.Text

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
            ImageConfig.URL1,
            ImageConfig.URL2,
            ImageConfig.URL3,
            ImageConfig.URL4,
            ImageConfig.URL5,
            ImageConfig.URL1,
            ImageConfig.URL2,
            ImageConfig.URL3,
            ImageConfig.URL4,
            ImageConfig.URL5,
            ImageConfig.URL1,
            ImageConfig.URL2,
            ImageConfig.URL3,
            ImageConfig.URL4,
            ImageConfig.URL5,
            ImageConfig.URL1,
            ImageConfig.URL2,
            ImageConfig.URL3,
            ImageConfig.URL4,
            ImageConfig.URL5
        )
        val names = arrayOf(
            "组织中心",
            "企业控制台",
            "账号中心",
            "用户中心",
            "消息中心",
            "产品中心",
            "合作中心",
            "应用中心",
            "服务中心",
            "项目中心",
            "组织中心",
            "企业控制台",
            "账号中心",
            "用户中心",
            "消息中心",
            "产品中心",
            "合作中心",
            "应用中心",
            "服务中心",
            "项目中心"
        )
        val ids = arrayOf(
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
        )
        for ((index, id) in ids.withIndex()) {
            data.add(ImgInfo(id, urls[index]))
            data.add(TextInfo(id, names[index]))
        }
    }

    private fun setAdapter() {
        val adapter = MultiTypeAdapter()
//        adapter.register(TextInfo::class.java, TextBinder())
        adapter.register(ImgInfo::class.java, ImgBinder())
        adapter.register(TextInfo::class.java).to(TextBinder(), TextBinder2(), TextBinder3())
            .withLinker { position, item ->
                return@withLinker when {
                    item.id % 2 == 0 -> 0
                    item.id % 3 == 0 -> 1
                    else -> 2
                }
            }
        dataBinding.recycler.adapter = adapter
        adapter.items = data
        dataBinding.recycler.layoutManager = LinearLayoutManager(this)
    }
}
