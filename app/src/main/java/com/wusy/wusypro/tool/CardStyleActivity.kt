package com.wusy.wusypro.tool

import com.wusy.wusylibrary.base.BaseActivity
import com.wusy.wusypro.R
import kotlinx.android.synthetic.main.activity_card_style.*

class CardStyleActivity:BaseActivity(){
    override fun getContentViewId(): Int {
        return R.layout.activity_card_style
    }

    override fun findView() {
    }

    override fun init() {
        titleView.setTitle("卡片样式")
            .showBackButton(true,this)
            .build()
    }

}
