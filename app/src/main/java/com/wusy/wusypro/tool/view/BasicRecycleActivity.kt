package com.wusy.wusypro.tool.view

import androidx.recyclerview.widget.LinearLayoutManager
import com.wusy.wusylibrary.base.BaseActivity
import com.wusy.wusypro.R
import com.wusy.wusyproject.tool.presenter.BasicRecycleAdapter
import kotlinx.android.synthetic.main.activity_basic_recycl.*

class BasicRecycleActivity : BaseActivity() {
    lateinit var adapter: BasicRecycleAdapter
    override fun getContentViewId(): Int {
        return R.layout.activity_basic_recycl
    }

    override fun findView() {

    }

    override fun init() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = BasicRecycleAdapter(this)
        adapter.list = initData()
        recyclerView.adapter = adapter

        refreshLayout.isEnableLoadMore = false
        refreshLayout.isEnableRefresh = false
    }

    private fun initData(): ArrayList<BasicRecycleAdapter.BasicRecycleBean> {
        val list = ArrayList<BasicRecycleAdapter.BasicRecycleBean>()
        list.add(BasicRecycleAdapter.BasicRecycleBean().apply {
            title="ConfirmPop"
            information="(确认弹窗：提供确认判断)"
        })
        list.add(BasicRecycleAdapter.BasicRecycleBean().apply {
            title="MultistageMultipleChoiceRecyclerPop"
            information="(多级多选列表弹窗：左右侧弹出，一个list实现，通过数据控制显隐，可用于筛选)"
        })
        return list
    }

}
