package com.wusy.wusypro.tool.BasicRecycleList

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wusy.wusylibrary.base.BaseActivity
import com.wusy.wusylibrary.base.BaseRecyclerAdapter
import com.wusy.wusylibrary.base.BaseViewHolder
import com.wusy.wusypro.R
import com.wusy.wusypro.app.ApplicationModel
import com.wusy.wusypro.app.Contants
import kotlinx.android.synthetic.main.activity_basic_recycl.*
import kotlinx.android.synthetic.main.layout_search.*

class BasicRecycleListActivity : BaseActivity() {
    private lateinit var model: ApplicationModel
    private lateinit var adapter:BasicRecycleListAdapter
    private var pageIndex = 0
    private var mProgramName = ""
    override fun findView() {
    }

    override fun init() {
        titleView.setTitle("基础列表")
            .showBackButton(true, this)
            .build()

        model = ApplicationModel()
        adapter = BasicRecycleListAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        refreshLayout.setOnRefreshListener {
            getProList(isClear = true, isShowAnim = false)

        }
        refreshLayout.setOnLoadMoreListener {
            pageIndex++
            getProList(isClear = false, isShowAnim = false)

        }
        getProList(isClear = true, isShowAnim = true)
        edSearch.setOnEditorActionListener { v, actionId, event ->
            when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    mProgramName=edSearch.text.toString()
                    getProList(isClear = true, isShowAnim = true)
                    //关闭软键盘
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(edSearch.windowToken, 0)
                }
            }
            false
        }
    }


    override fun getContentViewId(): Int {
        return R.layout.activity_basic_recycl
    }

    private val handler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            when (msg?.what ?: Contants.OKHTTP_REQUEST_ERROR) {
                Contants.OKHTTP_REQUEST_ERROR -> {
                    showToast(msg?.obj.toString())
                }
                Contants.OKHTTP_REQUEST_SUCCESS -> {
                    when (msg?.obj) {
                        is ProgramBean -> {
                            val bean = msg.obj as ProgramBean
                            adapter.list.addAll((bean.data!!.rows) as ArrayList)
                            if (bean.data!!.rows!!.size<Contants.pageSize) refreshLayout.setNoMoreData(true)
                            adapter.notifyDataSetChanged()
                        }
                    }
                }
            }
            refreshLayout.finishLoadMore()
            refreshLayout.finishRefresh()
            hideLoadImage()
        }
    }

    private fun getProList(isClear: Boolean, isShowAnim: Boolean) {
        if (isClear) {
            pageIndex = 0
            refreshLayout.setNoMoreData(false)
            adapter.list.clear()
        }
        if (isShowAnim) showLoadImage()
        model.getToolProList(handler,pageIndex,mProgramName)
    }
    inner class BasicRecycleListAdapter(context:Context) : BaseRecyclerAdapter<ProgramBean.DataBean.RowsBean>(context) {
        override fun onMyCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
            return ToolSelectProViewHolder(
                LayoutInflater.from(context)
                    .inflate(R.layout.item_basic_recycl, parent, false)
            )
        }

        override fun onMyBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
            if (holder is ToolSelectProViewHolder) {
                val thisHolder = holder as ToolSelectProViewHolder
                list[position]?.run {
                    thisHolder.tvName.text=this.programName
                }
            }
        }
    }
    inner class ToolSelectProViewHolder(itemView: View): BaseViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvName)
    }
}
