package com.wusy.wusylibrary.pop

import android.content.Context
import android.view.View
import android.view.animation.Animation
import android.widget.TextView
import com.wusy.wusylibrary.R
import razerdp.basepopup.BasePopupWindow

class SingleEasyChoicePop(context: Context) : BasePopupWindow(context) {
    private var tvYes = findViewById<TextView>(R.id.tvYes)
    private var tvNo = findViewById<TextView>(R.id.tvNo)
    var listener:OnItemClickListener?=null
    init {
        setBlurBackgroundEnable(true)
        tvYes.setOnClickListener {
            if(listener!=null) listener!!.onClickTop()
            dismiss()
        }
        tvNo.setOnClickListener {
            if(listener!=null) listener!!.onClickBottom()
            dismiss()
        }
    }

    override fun onCreateShowAnimation(): Animation {
        return getTranslateVerticalAnimation(1f, 0f, 250)
    }

    override fun onCreateDismissAnimation(): Animation {
        return getTranslateVerticalAnimation(0f, 1f, 250)
    }

    override fun onCreateContentView(): View {
        return createPopupById(R.layout.pop_single_easy_choice)
    }

    fun init(yesListen:View.OnClickListener,noListen:View.OnClickListener) {
        tvYes.setOnClickListener(yesListen)
        tvNo.setOnClickListener(noListen)
    }
    interface OnItemClickListener {
        fun onClickTop()
        fun onClickBottom()
    }
}

