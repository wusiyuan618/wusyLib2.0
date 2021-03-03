package com.wusy.wusylibrary.pop

import android.content.Context
import android.view.Gravity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wusy.wusylibrary.R
import com.wusy.wusylibrary.base.BaseActivity
import com.wusy.wusylibrary.base.BaseRecyclerAdapter
import razerdp.basepopup.BasePopupWindow

class SingleRecyclerChoicePop(context: Context) : BasePopupWindow(context) {
    private var recyclerView = contentView.findViewById<RecyclerView>(R.id.recyclerView)
    private var edSearch = contentView.findViewById<EditText>(R.id.edSearch)

    var searchName = ""
    var adapter = ProSelectAdapter(context)
    var listener: OnItemClickListener? = null
    var allList=ArrayList<PopSelectBean>()
    init {
        setBlurBackgroundEnable(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        popupGravity = Gravity.CENTER
        adapter.setOnRecyclerItemClickLitener(object :BaseRecyclerAdapter.onRecyclerItemClickLitener{
            override fun onRecyclerItemClick(view: RecyclerView.ViewHolder?, position: Int) {
                for(item in adapter.list){
                    item.isSelect=false
                }
                adapter.list[position].isSelect=true
                adapter.notifyDataSetChanged()
                listener?.run { 
                    onItemClick(adapter.list[position])
                }
                dismiss()
            }

            override fun onRecyclerItemLongClick(view: RecyclerView.ViewHolder?, position: Int) {
               
            }

        })
        edSearch.setOnEditorActionListener { v, actionId, event ->
            when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    searchName=edSearch.text.toString()
                    adapter.list=searchList(searchName)
                    adapter.notifyDataSetChanged()
                    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(edSearch.windowToken, 0)
                }
            }
            false
        }
        allList = PopSelectBean.createTestData()
        adapter.list = allList
        adapter.notifyDataSetChanged()

    }
    fun init(list:ArrayList<PopSelectBean>){
        allList=list
        adapter.list=list
        adapter.notifyDataSetChanged()
    }
    private fun searchList(name:String):ArrayList<PopSelectBean>{
        val list=ArrayList<PopSelectBean>()
        for (item in allList){
            if(item.name.contains(name)){
                list.add(item)
            }
        }
        return list
    }


    override fun onCreateShowAnimation(): Animation {
        return getDefaultAlphaAnimation(true)
    }

    override fun onCreateDismissAnimation(): Animation {
        return getDefaultAlphaAnimation(false)
    }

    override fun onCreateContentView(): View {
        return createPopupById(R.layout.pop_single_recycler_choice)
    }

    inner class ProSelectAdapter(context: Context) :
        BaseRecyclerAdapter<PopSelectBean>(context) {
        override fun onMyBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
            if (holder is ProSelectViewHolder) {
                val thisHolder = holder as ProSelectViewHolder
                list[position]?.run {
                    if (isSelect) thisHolder.iv.setBackgroundResource(R.mipmap.btn_choose_selected)
                    else thisHolder.iv.setBackgroundResource(R.mipmap.btn_choose_normal)
                    thisHolder.tv.text = "$name"
                }
            }
        }

        override fun onMyCreateViewHolder(
            parent: ViewGroup?,
            viewType: Int
        ): RecyclerView.ViewHolder {
            return ProSelectViewHolder(
                LayoutInflater.from(context)
                    .inflate(R.layout.item_pop_single_recyler_choice, parent, false)
            )
        }

    }

    inner class ProSelectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv = itemView.findViewById<TextView>(R.id.tv)
        var iv = itemView.findViewById<ImageView>(R.id.iv)
    }

    interface OnItemClickListener {
        fun onItemClick(bean: PopSelectBean)
    }

}
