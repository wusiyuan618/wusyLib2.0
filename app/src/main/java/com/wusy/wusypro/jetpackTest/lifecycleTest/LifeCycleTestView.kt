package com.wusy.wusypro.jetpackTest.lifecycleTest

import android.os.Bundle
import android.util.Log
import com.wusy.wusylibrary.base.BaseActivity
import com.wusy.wusypro.R

class LifeCycleTestView :BaseActivity(){
    override fun getContentViewId(): Int {
        return R.layout.activity_test
    }

    override fun findView() {

    }

    override fun init() {
        TAG="LifeCycleTestView"
        Log.i(TAG,"onCreate")
        lifecycle.addObserver(MyLifeCycleObserver())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG,"onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG,"onPause")
    }

}