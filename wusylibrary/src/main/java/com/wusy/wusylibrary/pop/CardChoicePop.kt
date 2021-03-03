package com.wusy.wusylibrary.pop

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.wusy.wusylibrary.R
import com.wusy.wusylibrary.view.TagLayout
import razerdp.basepopup.BasePopupWindow

class CardChoicePop(context: Context) : BasePopupWindow(context) {
    private var ivCancel = findViewById<ImageView>(R.id.ivCancel)
    private var tags = findViewById<TagLayout>(R.id.tags)
    var listener:OnItemClickListener?=null
    init {
        setBlurBackgroundEnable(true)
        popupGravity = Gravity.BOTTOM
        ivCancel.setOnClickListener {
            dismiss()
        }
        init(PopSelectBean.createTestData())
    }

    fun init(list: ArrayList<PopSelectBean>) {
        tags.removeAllViews()
        for (index in list.indices){
                val bean=list[index]
                val layout=LayoutInflater.from(context).inflate(R.layout.item_pop_card_choice,null)
                val tv=layout.findViewById<TextView>(R.id.tv)
                val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                params.setMargins(20, 20, 0, 0)
                tv.text=bean.name
                layout.setOnClickListener {
                    if(listener!=null) listener!!.onItemClick(bean,index)
                    dismiss()
                }

                tags.addView(layout,params)
        }
    }
    override fun onCreateShowAnimation(): Animation {
        return getTranslateVerticalAnimation(1f, 0f, 250)
    }

    override fun onCreateDismissAnimation(): Animation {
        return getTranslateVerticalAnimation(0f, 1f, 250)
    }

    override fun onCreateContentView(): View {
        return createPopupById(R.layout.pop_card_choice)
    }

    interface OnItemClickListener{
        fun onItemClick(bean:PopSelectBean,position:Int)
    }
}
