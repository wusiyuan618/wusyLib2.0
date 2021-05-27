package com.wusy.wusypro.tool.PopList

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wusy.wusylibrary.base.BaseActivity
import com.wusy.wusylibrary.base.BaseRecyclerAdapter
import com.wusy.wusypro.R
import kotlinx.android.synthetic.main.activity_basic_recycl.*

class PopListActivity : BaseActivity() {
    lateinit var adapter: PopListAdapter
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
        adapter = PopListAdapter(this)
        adapter.list = initData()
        recyclerView.adapter = adapter

        refreshLayout.isEnableLoadMore = false
        refreshLayout.isEnableRefresh = false
        initItemClick()
    }

    private fun initData(): ArrayList<PopListAdapter.PopListBean> {
        val list = ArrayList<PopListAdapter.PopListBean>()
        list.add(PopListAdapter.PopListBean().apply {
            title = "ConfirmPop"
            information = "(确认弹窗：提供确认判断)"
        })
        list.add(PopListAdapter.PopListBean().apply {
            title = "SingleRecyclerChoicePop"
            information = "(单选弹窗，带搜索，可用于项目选择)"
        })
        list.add(PopListAdapter.PopListBean().apply {
            title = "SingleEasyChoicePop"
            information = "(简单单选弹窗，View侧边弹出)"
        })
        list.add(PopListAdapter.PopListBean().apply {
            title = "SingleCardChoicePop"
            information = "(卡片样式选择器，宽度自适应)"
        })
        list.add(PopListAdapter.PopListBean().apply {
            title = "OneLevelWheelChoicePop"
            information = "(一级滚轮选择器，从底部弹出)"
        })
        list.add(PopListAdapter.PopListBean().apply {
            title = "TwoLevelWheelChoicePop"
            information = "(二级滚轮选择器，从底部弹出，一二级数据联动)"
        })
        list.add(PopListAdapter.PopListBean().apply {
            title = "SelectItemFromListPop"
            information = "(多级多选列表弹窗：左右侧弹出，一个list实现，通过数据控制显隐，可用于筛选)"
        })

        list.add(PopListAdapter.PopListBean().apply {
            title = "DateChoicePop"
            information = "(日期选择器，支持选择起始日期和结束日期)"
        })
        list.add(PopListAdapter.PopListBean().apply {
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
                    "DateChoicePop" -> {
                        createPopUtil.showDateChoicePop()
                    }
                    "DateChoiceCalendarPop" -> {
                        createPopUtil.showCaledarPop()
                    }
                }
            }

            override fun onRecyclerItemLongClick(view: RecyclerView.ViewHolder?, position: Int) {

            }

        })
    }


}
