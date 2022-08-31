package com.wusy.wusypro.home.view

import android.util.Log
import android.view.View
import com.wusy.wusylibrary.base.BaseFragment
import com.wusy.wusypro.R
import com.wusy.wusypro.study.CoroutineScopeStudy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeFragment :BaseFragment(){
    override fun findView(view: View?) {

    }

    override fun init() {
        CoroutineScopeStudy().test2()
    }

    override fun getContentViewId(): Int {
        return R.layout.fragment_home
    }






}
