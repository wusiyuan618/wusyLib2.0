package com.wusy.wusylibrary.pop

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.view.animation.Animation
import android.widget.TextView
import com.aigestudio.wheelpicker.WheelPicker
import com.wusy.wusylibrary.R
import razerdp.basepopup.BasePopupWindow

class WheelChoicePop(context: Context) : BasePopupWindow(context) {
    private var wheelStart = contentView.findViewById<WheelPicker>(R.id.wheelStart)
    private var wheelEnd = contentView.findViewById<WheelPicker>(R.id.wheelEnd)
    private var tvCancel = contentView.findViewById<TextView>(R.id.tvCancel)
    private var tvOk = contentView.findViewById<TextView>(R.id.tvOk)
    private var tvTitle = contentView.findViewById<TextView>(R.id.tvTitle)
    var listener:OnItemClickListener?=null
    /**
     * 显示的滚轮组个数
     */
    private var showCount=1
    private var mList=ArrayList<PopSelectBean>()
    init {
        setBlurBackgroundEnable(true)
        popupGravity = Gravity.BOTTOM
        initWheel(wheelEnd)
        initWheel(wheelStart)
        wheelStart.setOnItemSelectedListener { picker, data, position ->
            val secondList=ArrayList<String>()
            for (child in mList[position].childList?:ArrayList()){
                secondList.add(child.name)
            }
            wheelEnd.data=secondList
        }
        tvCancel.setOnClickListener {
            dismiss()
        }
        tvOk.setOnClickListener {
            val bean= when(showCount){
                1->{
                    mList[wheelStart.currentItemPosition]

                }
                2->{
                    mList[wheelStart.currentItemPosition].childList?.get(wheelEnd.currentItemPosition)

                }
                else -> null
            }
            if(listener!=null) listener!!.onClickOk(bean,showCount)
        }
    }

    fun init(list:ArrayList<PopSelectBean>,showCount:Int) {
        this.showCount=showCount
        this.mList=list
        val firstList=ArrayList<String>()
        val secondList=ArrayList<String>()
        for (prent in list){
            firstList.add(prent.name)
        }
        wheelStart.data=firstList
        if(showCount==2&&list.size>0){
            for (child in list[0].childList?:ArrayList()){
                secondList.add(child.name)
            }
            wheelEnd.data=secondList
            wheelEnd.visibility=View.VISIBLE
        }else{
            wheelEnd.visibility=View.GONE
        }

    }
    fun setTitle(title:String){
        tvTitle.text=title
    }

    private fun initWheel(wheel: WheelPicker) {
        wheel.setIndicator(true)
        wheel.itemTextColor = context.resources.getColor(R.color.unCheckTextColor)
        wheel.selectedItemTextColor = context.resources.getColor(R.color.normalTextColor)
        wheel.visibleItemCount = 5
        wheel.setCurtain(true)
        wheel.setAtmospheric(true)
        wheel.isCurved = true
    }
    override fun onCreateShowAnimation(): Animation {
        return getTranslateVerticalAnimation(1f, 0f, 250)
    }

    override fun onCreateDismissAnimation(): Animation {
        return getTranslateVerticalAnimation(0f, 1f, 250)
    }

    override fun onCreateContentView(): View {
        return createPopupById(R.layout.pop_wheel_choice)
    }
    interface OnItemClickListener {
        fun onClickOk(bean :PopSelectBean?,showCount:Int)
    }
}
