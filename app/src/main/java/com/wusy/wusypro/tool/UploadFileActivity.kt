package com.wusy.wusypro.tool

import com.wusy.wusylibrary.base.BaseActivity
import com.wusy.wusylibrary.pop.ChoiceFilePop
import com.wusy.wusypro.R
import kotlinx.android.synthetic.main.activity_upload_file.*

class UploadFileActivity:BaseActivity(){
    override fun getContentViewId(): Int {
        return R.layout.activity_upload_file
    }

    override fun findView() {

    }

    override fun init() {
        titleView.setTitle("附件上传")
            .showBackButton(true,this)
            .showOKButton("上传附件",true){
                val pop=ChoiceFilePop(this)
                pop.showPopupWindow()
            }
            .build()

    }

}
