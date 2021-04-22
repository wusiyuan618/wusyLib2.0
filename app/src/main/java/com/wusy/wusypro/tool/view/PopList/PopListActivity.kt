package com.wusy.wusypro.tool.view.PopList

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wusy.wusylibrary.base.BaseActivity
import com.wusy.wusylibrary.base.BaseRecyclerAdapter
import com.wusy.wusylibrary.pop.*
import com.wusy.wusypro.R
import com.wusy.wusyproject.tool.presenter.BasicRecycleAdapter
import kotlinx.android.synthetic.main.activity_basic_recycl.*

class PopListActivity : BaseActivity() {
    lateinit var adapter: BasicRecycleAdapter
    lateinit var createPopUtil: CreatePopUtil
    override fun getContentViewId(): Int {
        return R.layout.activity_basic_recycl
    }

    override fun findView() {

    }

    override fun init() {
        titleView.setTitle("弹窗样式列表")
            .showBackButton(true, this)
            .build()

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = BasicRecycleAdapter(this)
        adapter.list = initData()
        recyclerView.adapter = adapter

        refreshLayout.isEnableLoadMore = false
        refreshLayout.isEnableRefresh = false
        initItemClick()
    }

    private fun initData(): ArrayList<BasicRecycleAdapter.BasicRecycleBean> {
        val list = ArrayList<BasicRecycleAdapter.BasicRecycleBean>()
        list.add(BasicRecycleAdapter.BasicRecycleBean().apply {
            title = "ConfirmPop"
            information = "(确认弹窗：提供确认判断)"
        })
        list.add(BasicRecycleAdapter.BasicRecycleBean().apply {
            title = "SingleRecyclerChoicePop"
            information = "(单选弹窗，带搜索，可用于项目选择)"
        })
        list.add(BasicRecycleAdapter.BasicRecycleBean().apply {
            title = "SingleEasyChoicePop"
            information = "(简单单选弹窗，View侧边弹出)"
        })
        list.add(BasicRecycleAdapter.BasicRecycleBean().apply {
            title = "SingleCardChoicePop"
            information = "(卡片样式选择器，宽度自适应)"
        })
        list.add(BasicRecycleAdapter.BasicRecycleBean().apply {
            title = "OneLevelWheelChoicePop"
            information = "(一级滚轮选择器，从底部弹出)"
        })
        list.add(BasicRecycleAdapter.BasicRecycleBean().apply {
            title = "TwoLevelWheelChoicePop"
            information = "(二级滚轮选择器，从底部弹出，一二级数据联动)"
        })
        list.add(BasicRecycleAdapter.BasicRecycleBean().apply {
            title = "SelectItemFromListPop"
            information = "(多级多选列表弹窗：左右侧弹出，一个list实现，通过数据控制显隐，可用于筛选)"
        })

        list.add(BasicRecycleAdapter.BasicRecycleBean().apply {
            title = "DateChoicePop"
            information = "(日期选择器，支持选择起始日期和结束日期)"
        })
        list.add(BasicRecycleAdapter.BasicRecycleBean().apply {
            title = "DateChoiceWheelPop"
            information = "(日期选择器，滚轮样式)"
        })
        list.add(BasicRecycleAdapter.BasicRecycleBean().apply {
            title = "DateChoiceCalendarPop"
            information = "(日期选择器，日历样式)"
        })
        return list
    }

    private fun initItemClick() {
        createPopUtil = CreatePopUtil(this)
        adapter.setOnRecyclerItemClickLitener(object :
            BaseRecyclerAdapter.onRecyclerItemClickLitener {
            override fun onRecyclerItemClick(view: RecyclerView.ViewHolder?, position: Int) {
                when (adapter.list[position].title) {
                    "ConfirmPop" -> {
                        createPopUtil.showConfirmPop()
                    }
                    "SingleRecyclerChoicePop" -> {
                        createPopUtil.showSingleRecyclerChoicePop()
                    }
                    "SingleEasyChoicePop" -> {
                        createPopUtil.showSingleEasyChoicePop()
                    }
                    "SingleCardChoicePop" -> {
                        createPopUtil.showCardChoicePop()
                    }
                    "OneLevelWheelChoicePop" -> {
                        createPopUtil.showWheelChoicePop(1)
                    }
                    "TwoLevelWheelChoicePop" -> {
                        createPopUtil.showWheelChoicePop(2)
                    }
                    "SelectItemFromListPop" -> {
                        createPopUtil.showSelectItemFromListPop()
                    }
                }
            }

            override fun onRecyclerItemLongClick(view: RecyclerView.ViewHolder?, position: Int) {

            }

        })
    }


}
