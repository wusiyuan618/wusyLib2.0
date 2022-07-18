package com.wusy.wusypro.study

import androidx.recyclerview.widget.LinearLayoutManager
import com.wusy.wusylibrary.base.BaseActivity
import com.wusy.wusypro.R
import com.wusy.wusypro.app.BasicRecycleListAdapter
import kotlinx.android.synthetic.main.activity_adsorption.*
import kotlinx.android.synthetic.main.activity_basic_recycl.*
import kotlinx.android.synthetic.main.activity_basic_recycl.recyclerView

class AdsorptionActivity :BaseActivity() {
    private lateinit var adapter: BasicRecycleListAdapter
    override fun getContentViewId(): Int {
        return R.layout.activity_adsorption
    }

    override fun findView() {

    }

    override fun init() {
        adapter = BasicRecycleListAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter.list=getList()
        recyclerView.adapter =adapter
        toolbar.title = "我是标题"
    }
    fun getList():ArrayList<String>{
        val list=ArrayList<String>()
        for(item in 0..20){
            list.add("第${item}个")
        }
        return list
    }

}