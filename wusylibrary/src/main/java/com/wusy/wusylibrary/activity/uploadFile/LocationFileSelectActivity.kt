package com.wusy.wusylibrary.activity.uploadFile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.wusy.wusylibrary.base.BaseActivity
import kotlinx.android.synthetic.main.activity_local_file_select.*
import kotlinx.android.synthetic.main.activity_local_file_select.titleView
import com.hz.android.fileselector.FileSelectorView
import com.wusy.wusylibrary.R
import java.io.File


class LocationFileSelectActivity :BaseActivity(){
    override fun getContentViewId(): Int {
        return R.layout.activity_local_file_select
    }

    override fun findView() {

    }

    override fun init() {
        titleView.setTitle("请选择文件")
            .showBackButton(true, this)
            .build()
        fileSelectorView.setOnFileSelectedListener(object:FileSelectorView.OnFileSelectedListener{
            override fun onFilePathChanged(file: File?) {

            }

            override fun onSelected(selectedFile: File?) {
                val data=Intent()
                data.putExtras(Bundle().apply {
                    putString("filePath",selectedFile?.path?:"")
                })
                setResult(Activity.RESULT_OK,data)
                finish()
            }

        })
    }

}
