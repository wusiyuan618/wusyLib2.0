package com.wusy.wusypro.jetpackTest.lifecycleTest

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class MyLifeCycleObserver :LifecycleObserver{
    val TAG="MyLifeCycleObserver"
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun observerOnCreate(){
        Log.i(TAG,"onCreate")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun observerOnResume(){
        Log.i(TAG,"onResume")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun observerOnPause(){
        Log.i(TAG,"onPause")
    }
}