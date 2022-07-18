package com.wusy.wusypro.jetpackTest.viewModelWithLiveData

import androidx.lifecycle.Observer
import com.wusy.wusylibrary.base.BaseActivity
import com.wusy.wusypro.R
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_test.*

/**
 * ViewModel+LiveData
 * ViewModel提供数据保存，取代onSaveInstanceState（）的作用
 * LiveData做响应式编程，当数据发生改变时，自动更新UI
 */
class ViewModelWithLiveDataTestView :BaseActivity(){
    private var myViewModelWithLiveData: MyViewModelWithLiveData? = null
    override fun getContentViewId(): Int {
        return R.layout.activity_test
    }

    override fun findView() {

    }

    override fun init() {
        myViewModelWithLiveData = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            MyViewModelWithLiveData::class.java
        )
        //监听LiveData数据发生变化时更新数据
        myViewModelWithLiveData?.getLinkNumber()?.observe(this, Observer<Int?> {
            tv.text = it?.toString()
        })

        //更新LiveData的数据
        btn.setOnClickListener {
            myViewModelWithLiveData?.addLinkedNumber(1)
        }
    }

}