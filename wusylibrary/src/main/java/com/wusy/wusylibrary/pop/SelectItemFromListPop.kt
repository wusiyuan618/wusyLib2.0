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
import razerdp.basepopup.BasePopupWindow
import java.io.Serializable
import java.lang.Exception

class SelectItemFromListPop(context: Context) : BasePopupWindow(context) {
    lateinit var adapter: ACMSelectPopAdapter
    private var selectRecyclerView = contentView.findViewById<RecyclerView>(R.id.selectRecyclerView)
    private var btnOk = contentView.findViewById<TextView>(R.id.btnOk)
    var listener:OnItemClickListener?=null
    init {
        setBlurBackgroundEnable(true)
        popupGravity = Gravity.RIGHT
        btnOk.setOnClickListener {
            val list = ArrayList<ACMSelectPopBean>()
            for (item in adapter.list) {
                when (item.showStatus) {
                    3 -> {//多选
                        if (!item.hasChild && item.isSelect && item.id != "") {
                            list.add(item)
                        }
                    }
                    2 -> {//单选
                        if (item.isSelect && item.id != "") {
                            list.add(item)
                        }
                    }
                }
            }
            if(listener!=null) listener!!.onItemClick(list)
            dismiss()
        }
    }

    fun init(list: ArrayList<ACMSelectPopBean>) {
        adapter = ACMSelectPopAdapter(context)
        selectRecyclerView.layoutManager = LinearLayoutManager(context)
        if (list.size != 0) {
            adapter.list = list
        }
        selectRecyclerView.adapter = adapter

    }

    fun init() {
        adapter = ACMSelectPopAdapter(context)
        selectRecyclerView.layoutManager = LinearLayoutManager(context)
        adapter.list = initTestData()
        selectRecyclerView.adapter = adapter
    }

    private fun initTestData(): ArrayList<ACMSelectPopBean> {
        val list = ArrayList<ACMSelectPopBean>()
        list.add(ACMSelectPopBean().apply {
            id = "1"
            showStatus = 1
            name = "全部检查小组"
            titleShow = "全部检查小组"
            parentId = ""
            titleId = ""
            isShow = true
            hasChild = true
        })
        list.add(ACMSelectPopBean().apply {
            id = "2"
            showStatus = 2
            name = "全部"
            isSelect = true
            parentId = "1"
            titleId = "1"
        })
        list.add(ACMSelectPopBean().apply {
            id = "3"
            showStatus = 2
            name = "检查组1"
            parentId = "1"
            titleId = "1"
        })
        list.add(ACMSelectPopBean().apply {
            id = "4"
            showStatus = 2
            name = "检查组2"
            parentId = "1"
            titleId = "1"
        })
        list.add(ACMSelectPopBean().apply {
            id = "5"
            showStatus = 1
            name = "全部区域"
            titleShow = "全部区域"
            parentId = ""
            titleId = ""
            isShow = true
            hasChild = true
        })
        list.add(ACMSelectPopBean().apply {
            id = "6"
            showStatus = 2
            name = "全部"
            parentId = "5"
            titleId = "5"
        })
        list.add(ACMSelectPopBean().apply {
            id = "7"
            showStatus = 2
            name = "B栋"
            parentId = "5"
            titleId = "5"
            hasChild = true
        })
        list.add(ACMSelectPopBean().apply {
            id = "12"
            showStatus = 2
            name = "全部"
            parentId = "7"
            titleId = "5"
        })
        list.add(ACMSelectPopBean().apply {
            id = "8"
            showStatus = 2
            name = "B-2F"
            parentId = "7"
            titleId = "5"
        })
        list.add(ACMSelectPopBean().apply {
            id = "9"
            showStatus = 2
            name = "B-3F"
            parentId = "7"
            titleId = "5"
            hasChild = true
        })
        list.add(ACMSelectPopBean().apply {
            id = "13"
            showStatus = 2
            name = "全部"
            parentId = "9"
            titleId = "5"
        })
        list.add(ACMSelectPopBean().apply {
            id = "10"
            showStatus = 2
            name = "B-3F-1"
            parentId = "9"
            titleId = "5"
        })
        list.add(ACMSelectPopBean().apply {
            id = "11"
            showStatus = 2
            name = "B-3F-2"
            parentId = "9"
            titleId = "5"
        })
        list.add(ACMSelectPopBean().apply {
            id = "14"
            showStatus = 1
            name = "检查项"
            titleShow = "检查项"
            parentId = ""
            titleId = ""
            hasChild = true
            isShow = true
        })
        list.add(ACMSelectPopBean().apply {
            id = "15"
            showStatus = 3
            name = "全部"
            parentId = "14"
            titleId = "14"
        })
        list.add(ACMSelectPopBean().apply {
            id = "16"
            showStatus = 3
            name = "混领土结构工程"
            parentId = "14"
            titleId = "14"
            hasChild = true
        })
        list.add(ACMSelectPopBean().apply {
            id = "17"
            showStatus = 3
            name = "切体工程"
            parentId = "14"
            titleId = "14"
            hasChild = true
        })
        list.add(ACMSelectPopBean().apply {
            id = "20"
            showStatus = 3
            name = "全部"
            parentId = "16"
            titleId = "14"
        })
        list.add(ACMSelectPopBean().apply {
            id = "18"
            showStatus = 3
            name = "截面尺寸"
            parentId = "16"
            titleId = "14"
        })
        list.add(ACMSelectPopBean().apply {
            id = "19"
            showStatus = 3
            name = "外门窗洞口尺寸宽"
            parentId = "16"
            titleId = "14"
        })
        list.add(ACMSelectPopBean().apply {
            id = "23"
            showStatus = 3
            name = "全部"
            parentId = "17"
            titleId = "14"
        })
        list.add(ACMSelectPopBean().apply {
            id = "21"
            showStatus = 3
            name = "表面平整度"
            parentId = "17"
            titleId = "14"
        })
        list.add(ACMSelectPopBean().apply {
            id = "22"
            showStatus = 3
            name = "垂直度"
            parentId = "17"
            titleId = "14"
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


    class ACMSelectPopAdapter(context: Context) : BaseRecyclerAdapter<ACMSelectPopBean>(
        context
    ) {
        companion object {
            val allStr = "全部"
        }

        override fun onMyCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): RecyclerView.ViewHolder {
            return when (viewType) {
                1 -> ACMSelectPopTitleHolder(
                    LayoutInflater.from(context).inflate(
                        R.layout.item_pop_select_from_list_title,
                        parent,
                        false
                    )
                )
                2 -> ACMSelectPopDanXuanHolder(
                    LayoutInflater.from(context).inflate(
                        R.layout.item_pop_select_from_list_single,
                        parent,
                        false
                    )
                )
                else -> ACMSelectPopDuoXuanHolder(
                    LayoutInflater.from(context).inflate(
                        R.layout.item_pop_select_from_list_multiple,
                        parent,
                        false
                    )
                )
            }
        }

        override fun onMyBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

            if (holder is ACMSelectPopTitleHolder) {
                var thisHolder = holder as ACMSelectPopTitleHolder
                list[position]?.run {
                    thisHolder.tvName.text = titleShow
                    if (isSelect) {
                        thisHolder.ivIcon.setImageResource(R.mipmap.arrow_top)
                    } else {
                        thisHolder.ivIcon.setImageResource(R.mipmap.arrow_bottom)
                    }
                    thisHolder.rlTitle.setOnClickListener {
                        if (!hasChild) {
                            Toast.makeText(context, "没有更多了", Toast.LENGTH_SHORT).show()
                            return@setOnClickListener
                        }
                        if (isSelect) {
                            thisHolder.ivIcon.setImageResource(R.mipmap.arrow_bottom)
                            hideAllChilders(this)
                            thisHolder.llBack.visibility = View.GONE
                            backItemList.clear()
                        } else {
                            thisHolder.ivIcon.setImageResource(R.mipmap.arrow_top)
                            for (item in findChilders(id)) {
                                item.isShow = true
                            }
                        }
                        notifyDataSetChanged()
                        isSelect = !isSelect

                    }
                    thisHolder.llBack.setOnClickListener {
                        if (backItemList.size == 0) return@setOnClickListener
                        hideAllChilders(this)
                        for (item in findChilders(backItemList[backItemList.size - 1].parentId)) {
                            item.isShow = true
                        }
                        backItemList.removeAt(backItemList.size - 1)
                        if (backItemList.size == 0) {
                            thisHolder.llBack.visibility = View.GONE
                        } else {
                            thisHolder.llBack.visibility = View.VISIBLE
                            thisHolder.tvBack.text = backItemList[backItemList.size - 1].name
                        }
                        notifyDataSetChanged()
                    }
                    if (isShow) {
                        thisHolder.setVisibility(View.VISIBLE)
                    } else {
                        thisHolder.setVisibility(View.GONE)
                    }
                    if (backItemList.size == 0) {
                        thisHolder.llBack.visibility = View.GONE
                    } else {
                        thisHolder.llBack.visibility = View.VISIBLE
                        thisHolder.tvBack.text = backItemList[backItemList.size - 1].name
                    }
                }
            } else if (holder is ACMSelectPopDanXuanHolder) {
                var thisHolder = holder as ACMSelectPopDanXuanHolder
                list[position]?.run {
                    thisHolder.tvName.text = name
                    thisHolder.itemView.setOnClickListener {
                        val title: ACMSelectPopBean? =
                            findBeanById(titleId) ?: return@setOnClickListener
                        if (hasChild) {
                            hideAllChilders(title!!)
                            for (item in findChilders(id)) {
                                item.isShow = true
                            }
                            title.backItemList.add(this)
                        } else {
                            hideAllChilders(title!!)
                            hideAllChildersSelect(title)
                            title.isSelect = false
                            title.backItemList.clear()
                            if (name == allStr) {
                                title.titleShow = findBeanById(parentId)?.name ?: ""
                                var parent = findBeanById(parentId)
                                if (parent?.showStatus != 1) {
                                    parent?.isSelect = true
                                }
                            } else {
                                title.titleShow = name
                                this.isSelect = true
                            }

                        }
                        notifyDataSetChanged()
                    }
                    if (isShow) {
                        thisHolder.setVisibility(View.VISIBLE)
                    } else {
                        thisHolder.setVisibility(View.GONE)
                    }
                }
            } else if (holder is ACMSelectPopDuoXuanHolder) {
                var thisHolder = holder as ACMSelectPopDuoXuanHolder
                list[position]?.run {
                    thisHolder.tvName.text = name
                    var selectCount = 0
                    var allCount = 0
                    if (name == allStr) {
                        for (item in findChilders(parentId)) {
                            if (item.name == allStr) continue
                            allCount++
                            if (item.isSelect) {
                                selectCount++
                            }
                        }
                        if (allCount > 0 && selectCount == 0) {//全部未选
                            thisHolder.ivIcon.setImageResource(R.mipmap.choose_normal_quality)
                            isSelect = false
                        } else if (allCount > 0 && allCount == selectCount) {//全选
                            thisHolder.ivIcon.setImageResource(R.mipmap.choose_selected_quality)
                            isSelect = true
                        } else {//选中一些
                            thisHolder.ivIcon.setImageResource(R.mipmap.choose_noall_quality)
                            isSelect = false
                        }
                    } else {
                        if (hasChild) {
                            for (item in findChilders(id)) {
                                if (item.name == allStr) continue
                                allCount++
                                if (item.isSelect) {
                                    selectCount++
                                }
                            }
                            if (allCount > 0 && selectCount == 0) {//全部未选
                                thisHolder.ivIcon.setImageResource(R.mipmap.choose_normal_quality)
                                isSelect = false
                            } else if (allCount > 0 && allCount == selectCount) {//全选
                                thisHolder.ivIcon.setImageResource(R.mipmap.choose_selected_quality)
                                isSelect = true
                            } else {//选中一些
                                thisHolder.ivIcon.setImageResource(R.mipmap.choose_noall_quality)
                                isSelect = false
                            }
                        } else {
                            if (isSelect) {
                                thisHolder.ivIcon.setImageResource(R.mipmap.choose_selected_quality)
                            } else {
                                thisHolder.ivIcon.setImageResource(R.mipmap.choose_normal_quality)
                            }
                        }
                    }
                    thisHolder.ivIcon.setOnClickListener {
                        isSelect = !isSelect
                        if (name == allStr) {
                            var bean = findBeanById(parentId)
                            if (bean != null) {
                                for (item in findAllChilders(bean)) {
                                    item.isSelect = isSelect
                                }
                            }
                        } else if (hasChild) {
                            for (item in findAllChilders(this)) {
                                item.isSelect = isSelect
                            }
                        }
                        notifyDataSetChanged()
                    }
                    thisHolder.itemView.setOnClickListener {
                        val title: ACMSelectPopBean? =
                            findBeanById(titleId) ?: return@setOnClickListener
                        if (hasChild) {
                            hideAllChilders(title!!)
                            for (item in findChilders(id)) {
                                item.isShow = true
                            }
                            title.backItemList.add(this)
                        }
                        notifyDataSetChanged()
                    }
                    if (isShow) {
                        thisHolder.setVisibility(View.VISIBLE)
                    } else {
                        thisHolder.setVisibility(View.GONE)
                    }
                }
            }
        }

        private fun findBeanById(id: String): ACMSelectPopBean? {
            if (id == "") return null
            for (item in list) {
                if (id == item.id) {
                    return item
                }
            }
            return null
        }

        private fun findChilders(id: String): ArrayList<ACMSelectPopBean> {
            val childrens = ArrayList<ACMSelectPopBean>()
            if (id == "") return childrens
            for (item in list) {
                if (id == item.parentId) {
                    childrens.add(item)
                }
            }
            return childrens
        }

        /**
         * 收拢所有的子项（包括孙子项）
         */
        private fun hideAllChilders(parent: ACMSelectPopBean) {
            var list = findChilders(parent.id)
            for (item in list) {
                item.isShow = false
                hideAllChilders(item)
            }
        }

        /**
         * 关闭所有的子项的选中状态（包括孙子项）
         * 用于单选
         */
        private fun hideAllChildersSelect(parent: ACMSelectPopBean) {
            var list = findChilders(parent.id)
            for (item in list) {
                item.isSelect = false
                if (item.hasChild) {
                    hideAllChildersSelect(item)
                }
            }
        }

        /**
         * 检索所有子集和孙子集
         */
        private fun findAllChilders(item: ACMSelectPopBean): ArrayList<ACMSelectPopBean> {
            var list = ArrayList<ACMSelectPopBean>()
            if (!item.hasChild) return list
            var childs = findChilders(item.id)
            for (item in childs) {
                if (item.hasChild) {
                    list.addAll(findAllChilders(item))
                }
            }
            list.addAll(childs)
            return list
        }

        override fun getItemViewType(position: Int): Int {
            return try {
                list[position].showStatus
            } catch (e: Exception) {
                0
            }
        }

        inner class ACMSelectPopTitleHolder(view: View) : RecyclerView.ViewHolder(view) {
            val tvName: TextView = view.findViewById(R.id.tvName)
            val ivIcon: ImageView = view.findViewById(R.id.ivIcon)
            val llBack: LinearLayout = view.findViewById(R.id.llBack)
            val tvBack: TextView = view.findViewById(R.id.tvBack)
            val rlTitle: RelativeLayout = view.findViewById(R.id.rlTitle)

            fun setVisibility(visibility: Int) {
                itemView.visibility = visibility
                var params = itemView.layoutParams as (RecyclerView.LayoutParams)
                if (visibility == View.VISIBLE) {
                    params.width = RecyclerView.LayoutParams.MATCH_PARENT
                    params.height = RecyclerView.LayoutParams.WRAP_CONTENT
                } else {
                    params.width = 0
                    params.height = 0
                }
                itemView.layoutParams = params
            }
        }

        inner class ACMSelectPopDanXuanHolder(view: View) : RecyclerView.ViewHolder(view) {
            val tvName: TextView = view.findViewById(R.id.tvName)
            fun setVisibility(visibility: Int) {
                itemView.visibility = visibility
                val params = itemView.layoutParams as (RecyclerView.LayoutParams)
                if (visibility == View.VISIBLE) {
                    params.width = RecyclerView.LayoutParams.MATCH_PARENT
                    params.height = RecyclerView.LayoutParams.WRAP_CONTENT
                } else {
                    params.width = 0
                    params.height = 0
                }
                itemView.layoutParams = params
            }
        }

        inner class ACMSelectPopDuoXuanHolder(view: View) : RecyclerView.ViewHolder(view) {
            val tvName: TextView = view.findViewById(R.id.tvName)
            val ivIcon: ImageView = view.findViewById(R.id.ivIcon)
            fun setVisibility(visibility: Int) {
                itemView.visibility = visibility
                var params = itemView.layoutParams as (RecyclerView.LayoutParams)
                if (visibility == View.VISIBLE) {
                    params.width = RecyclerView.LayoutParams.MATCH_PARENT
                    params.height = RecyclerView.LayoutParams.WRAP_CONTENT
                } else {
                    params.width = 0
                    params.height = 0
                }
                itemView.layoutParams = params
            }
        }
    }

    class ACMSelectPopBean : Serializable {
        var id = ""
        //        var level: Int = 1
        //1=标题 2=单选 3=多选
        var showStatus = 1
        var name: String = ""
        /**
         * 父级ID
         */
        var parentId: String = ""
        /**
         * 顶部标题ID
         */
        var titleId: String = ""
        var isSelect: Boolean = false
        var isShow: Boolean = false
        /**
         * 返回上一级的Item
         */
        var backItemList = ArrayList<ACMSelectPopBean>()
        var hasChild: Boolean = false
        var titleShow: String = ""
        /**
         * 主类型
         */
        var type: String = ""
        /**
         * 二级类型
         */
        var secType: String = ""
    }
    interface OnItemClickListener{
        fun onItemClick(list:ArrayList<ACMSelectPopBean>)
    }
}

