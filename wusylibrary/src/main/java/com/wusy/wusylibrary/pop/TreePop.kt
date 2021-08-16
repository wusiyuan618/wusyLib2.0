package com.wusy.wusylibrary.pop

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wusy.wusylibrary.R
import com.wusy.wusylibrary.base.BaseRecyclerAdapter
import com.wusy.wusylibrary.base.BaseViewHolder
import razerdp.basepopup.BasePopupWindow
import java.io.Serializable
import java.lang.Exception
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



class TreePop(context: Context) : BasePopupWindow(context) {
    lateinit var adapter: TreePopAdapter
    private var selectRecyclerView = contentView.findViewById<RecyclerView>(R.id.selectRecyclerView)
    private var btnOk = contentView.findViewById<TextView>(R.id.btnOk)
    var listener: OnItemClickListener? = null

    init {
        setBlurBackgroundEnable(true)
        popupGravity = Gravity.RIGHT
        btnOk.setOnClickListener {
            //这里是多选的数据反馈
            val thislist = ArrayList<TreePopBean>()
            for (item in adapter.list) {
                if(item.showStatus==1&&item.isSelect){
                    thislist.add(item)
                }
            }
            if (listener != null) listener!!.onItemClick(thislist)
            dismiss()
        }
        selectRecyclerView.layoutManager = LinearLayoutManager(context)
        adapter = TreePopAdapter(context)
        selectRecyclerView.adapter = adapter
        adapter.setShowEmptyView(false)
    }

    fun init() {
        adapter.list = initTestData()
        adapter.notifyDataSetChanged()
    }

    private fun initTestData(): ArrayList<TreePopBean> {
        val list = ArrayList<TreePopBean>()
        list.add(TreePopBean().apply {
            id = "1"
            showStatus = 0
            name = "1栋"
            parentId = ""
            isShow = true
            hasChild = true
            level=1
        })
        list.add(TreePopBean().apply {
            id = "3"
            showStatus = 0
            name = "1单元"
            parentId = "1"
            isShow = false
            hasChild = false
            level=2
        })
        list.add(TreePopBean().apply {
            id = "2"
            showStatus = 0
            name = "2栋"
            parentId = ""
            isShow = true
            hasChild = true
            level=1
        })

        list.add(TreePopBean().apply {
            id = "4"
            showStatus = 0
            name = "1单元"
            parentId = "2"
            isShow = false
            hasChild = true
            level=2
        })
        list.add(TreePopBean().apply {
            id = "6"
            showStatus = 0
            name = "1层"
            parentId = "4"
            isShow = false
            hasChild = false
            level=3
        })
        list.add(TreePopBean().apply {
            id = "5"
            showStatus = 0
            name = "2单元"
            parentId = "2"
            isShow = false
            hasChild = true
            level=2
        })
        list.add(TreePopBean().apply {
            id = "7"
            showStatus = 0
            name = "1层"
            parentId = "5"
            isShow = false
            hasChild = false
            level=3
        })
        list.add(TreePopBean().apply {
            id = "8"
            showStatus = 0
            name = "2层"
            parentId = "5"
            isShow = false
            hasChild = true
            level=3
        })
        list.add(TreePopBean().apply {
            id = "9"
            showStatus = 1
            name = "1号房"
            parentId = "8"
            isShow = false
            hasChild = false
            level = 4
        })
        list.add(TreePopBean().apply {
            id = "10"
            showStatus = 1
            name = "2号房"
            parentId = "8"
            isShow = false
            hasChild = false
            level = 4
        })
        list.add(TreePopBean().apply {
            id = "11"
            showStatus = 1
            name = "公区"
            parentId = "8"
            isShow = false
            hasChild = false
            level = 4
        })

        return list
    }

    override fun onCreateContentView(): View {
        return createPopupById(R.layout.pop_select_item_from_list)
    }


    override fun onCreateShowAnimation(): Animation {
        return AnimationUtils.loadAnimation(context, R.anim.right_to_center)
    }

    override fun onCreateDismissAnimation(): Animation {
        return AnimationUtils.loadAnimation(context, R.anim.center_to_right)
    }

    inner class TreePopAdapter(context: Context) : BaseRecyclerAdapter<TreePopBean>(context) {
        var isSingleSelct=false
        override fun onMyCreateViewHolder(
            parent: ViewGroup?,
            viewType: Int
        ): RecyclerView.ViewHolder {
            return when (viewType) {
                1-> TreePopSelectHolder(
                    LayoutInflater.from(context).inflate(
                        R.layout.item_tree_pop_select,
                        parent,
                        false
                    )
                )
                else -> TreePopTitleHolder(
                    LayoutInflater.from(context).inflate(
                        R.layout.item_tree_pop_title,
                        parent,
                        false
                    )
                )
            }
        }

        override fun onMyBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
            if (holder is TreePopTitleHolder) {
                val thisHolder = holder as TreePopTitleHolder
                list[position]?.run {
                    thisHolder.tvName.text=name
                    //设置间距
                    val lp = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    lp.leftMargin=60*(level-1)
                    thisHolder.view.layoutParams = lp
                    if(isShow){
                        thisHolder.setVisibility(View.VISIBLE)
                    }else{
                        thisHolder.setVisibility(View.GONE)
                    }

                    thisHolder.ll.setOnClickListener {
                        if(hasChild){
                            if(isOpen){//展开状态
                                val thisList=findAllChilds(id)
                                for (item in thisList){
                                    item.isShow=false
                                    item.isOpen=false
                                }
                            }else{//收起状态
                                val thisList=findChilds(id)
                                for (item in thisList){
                                    item.isShow=true
                                }
                            }
                            isOpen=!isOpen
                            notifyDataSetChanged()
                        }else{
                            Toast.makeText(context,"没有更多了",Toast.LENGTH_SHORT).show()
                        }

                    }
                    if(isSelect){
                        thisHolder.ivSelectAll.setImageResource(R.mipmap.choose_selected_quality)
                    }else{
                        thisHolder.ivSelectAll.setImageResource(R.mipmap.choose_normal_quality)
                    }
                    //多选，选中全部子项
                    thisHolder.ivSelectAll.setOnClickListener {
                        isSelect=!isSelect
                        if(isSelect){
                            thisHolder.ivSelectAll.setImageResource(R.mipmap.choose_selected_quality)
                        }else{
                            thisHolder.ivSelectAll.setImageResource(R.mipmap.choose_normal_quality)
                        }
                        val thisList=findAllChilds(id)
                        for (item in thisList){
                            item.isSelect=isSelect
                        }
                        notifyDataSetChanged()
                    }
                    //单选和多选判断
                    if(isSingleSelct){
                        thisHolder.ivSelectAll.visibility=View.GONE
                    }else{
                        thisHolder.ivSelectAll.visibility=View.VISIBLE
                    }
                }
            }else{
                val thisHolder = holder as TreePopSelectHolder
                list[position]?.run {
                    thisHolder.tvName.text=name
                    //设置间距
                    val lp = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    lp.leftMargin=60*(level-1)
                    thisHolder.view.layoutParams = lp
                    if(isShow){
                        thisHolder.setVisibility(View.VISIBLE)
                    }else{
                        thisHolder.setVisibility(View.GONE)
                    }
                    if(isSelect){
                        thisHolder.ivSelectAll.setImageResource(R.mipmap.choose_selected_quality)
                    }else{
                        thisHolder.ivSelectAll.setImageResource(R.mipmap.choose_normal_quality)
                    }
                    thisHolder.ll.setOnClickListener {
                        if(isSingleSelct){
                            val thisList=ArrayList<TreePopBean>()
                            if(listener!=null) listener!!.onItemClick(thisList)
                            dismiss()
                        }else{
                            isSelect=!isSelect
                            if(isSelect){
                                thisHolder.ivSelectAll.setImageResource(R.mipmap.choose_selected_quality)
                            }else{
                                thisHolder.ivSelectAll.setImageResource(R.mipmap.choose_normal_quality)
                            }
                        }

                    }
                }
            }
        }
        override fun getItemViewType(position: Int): Int {
            return try {
                list[position].showStatus
            } catch (e: Exception) {
                0
            }
        }

        private fun findChilds(parentId:String):ArrayList<TreePopBean>{
            val thisList=ArrayList<TreePopBean>()
            for(item in list){
                if(item.parentId==parentId){
                    thisList.add(item)
                }
            }
            return thisList
        }
        private fun findAllChilds(parentId:String):ArrayList<TreePopBean>{
            val thisList=ArrayList<TreePopBean>()
            for(item in list){
                if(item.parentId==parentId){
                    thisList.add(item)
                    thisList.addAll(findAllChilds(item.id))
                }
            }
            return thisList
        }
    }
    inner class TreePopTitleHolder(view: View) :  BaseViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvName)
        val ivIcon: ImageView = view.findViewById(R.id.ivIcon)
        val ivSelectAll: ImageView = view.findViewById(R.id.ivSelectAll)
        val ll: LinearLayout = view.findViewById(R.id.ll)
        val view: RelativeLayout = view.findViewById(R.id.view)

    }

    inner class TreePopSelectHolder(view: View) : BaseViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvName)
        val ivSelectAll: ImageView = view.findViewById(R.id.ivSelectAll)
        val ll: LinearLayout = view.findViewById(R.id.ll)
        val view: RelativeLayout = view.findViewById(R.id.view)
    }

    inner class TreePopBean : Serializable {
        var id = ""
        var level: Int = 1
        //0=标题 1=选择区
        var showStatus = 0
        var name: String = ""
        /**
         * 父级ID
         */
        var parentId: String = ""
        var isSelect: Boolean = false
        var isOpen=false
        var isShow: Boolean = false
        /**
         * 返回上一级的Item
         */
        var hasChild: Boolean = false

    }

    interface OnItemClickListener {
        fun onItemClick(list: ArrayList<TreePopBean>)
    }
}


