package com.wusy.wusypro.tool.view

import android.util.Log
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
            information = "(二级滚轮选择器，从底部弹出，IOS样式)"
        })
        list.add(BasicRecycleAdapter.BasicRecycleBean().apply {
            title = "MultistageLevelWheelChoicePop"
            information = "(多级滚轮选择器，从底部弹出，IOS样式)"
        })
        list.add(BasicRecycleAdapter.BasicRecycleBean().apply {
            title = "OneLevelMultipleChoiceRecyclerPop"
            information = "(一级多选列表弹窗：中部侧弹出，可用于添加)"
        })
        list.add(BasicRecycleAdapter.BasicRecycleBean().apply {
            title = "MultistageMultipleChoiceRecyclerPop"
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
        list.add(BasicRecycleAdapter.BasicRecycleBean().apply {
            title = "FileChoicePop"
            information = "(附件选择弹窗，从底部弹出，IOS样式)"
        })
        list.add(BasicRecycleAdapter.BasicRecycleBean().apply {
            title = "UploadProgressPop"
            information = "(进度显示弹窗，可用于数据上传)"
        })
        return list
    }

    private fun initItemClick() {
        adapter.setOnRecyclerItemClickLitener(object :
            BaseRecyclerAdapter.onRecyclerItemClickLitener {
            override fun onRecyclerItemClick(view: RecyclerView.ViewHolder?, position: Int) {
                when (adapter.list[position].title) {
                    "ConfirmPop" -> {
                        showConfirmPop()
                    }
                    "SingleRecyclerChoicePop" -> {
                        showSingleRecyclerChoicePop()
                    }
                    "SingleEasyChoicePop"->{
                        showSingleEasyChoicePop()
                    }
                    "SingleCardChoicePop"->{
                        showCardChoicePop()
                    }
                }
            }

            override fun onRecyclerItemLongClick(view: RecyclerView.ViewHolder?, position: Int) {

            }

        })
    }

    private var confirmPop: ConfirmPop? = null
    private fun showConfirmPop() {
        if (confirmPop == null) confirmPop=ConfirmPop(this@PopListActivity)

        confirmPop!!.listener = object : ConfirmPop.OnBtnClickListener {
            override fun clickOk() {
                showToast("点击了确认按钮")
                confirmPop!!.dismiss()
            }

            override fun clickCancel() {
                showToast("点击了取消按钮")
                confirmPop!!.dismiss()
            }
        }
        confirmPop!!.disListener = object : ConfirmPop.OnDismissListener {
            override fun onDismiss() {
                showToast("弹窗关闭")
            }
        }
        confirmPop!!.showPopupWindow()
    }

    private var singleRecyclerChoicePop: SingleRecyclerChoicePop? = null
    private fun showSingleRecyclerChoicePop() {
        if (singleRecyclerChoicePop == null) singleRecyclerChoicePop =
            SingleRecyclerChoicePop(this@PopListActivity)
        singleRecyclerChoicePop!!.listener=object:SingleRecyclerChoicePop.OnItemClickListener{
            override fun onItemClick(bean: PopSelectBean) {
                showToast("您选中了：${bean.name}")
            }
        }
        singleRecyclerChoicePop!!.showPopupWindow()
    }

    private var singleEasyChoicePop: SingleEasyChoicePop? = null
    private fun showSingleEasyChoicePop() {
        if (singleEasyChoicePop == null) singleEasyChoicePop =
            SingleEasyChoicePop(this@PopListActivity)
        singleEasyChoicePop!!.listener=object:SingleEasyChoicePop.OnItemClickListener{
            override fun onClickTop() {
                showToast("您点击了同意")
            }

            override fun onClickBottom() {
                showToast("您点击了拒绝")
            }
        }
        singleEasyChoicePop!!.showPopupWindow(titleView)
    }
    private var cardChoicePop: CardChoicePop? = null
    private fun showCardChoicePop() {
        if (cardChoicePop == null) cardChoicePop =
            CardChoicePop(this@PopListActivity)
        cardChoicePop!!.listener=object:CardChoicePop.OnItemClickListener{
            override fun onItemClick(bean: PopSelectBean, position: Int) {
                showToast("你选中了：${bean.name}")
            }
        }
        cardChoicePop!!.showPopupWindow()
    }
}
