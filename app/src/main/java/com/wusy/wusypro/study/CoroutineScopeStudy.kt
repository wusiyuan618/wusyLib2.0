package com.wusy.wusypro.study

import android.util.Log
import kotlinx.coroutines.*

class CoroutineScopeStudy {

    val TAG = "CoroutineScopeStudy"
    suspend fun getName(): String {
        Log.i(TAG, "开始获取名字")
        delay(4000)
        Log.i(TAG, "获取名字结束")
        return "小白"
    }

    suspend fun getAge(): Int {
        Log.i(TAG, "开始获取年龄")
        delay(1000)
        Log.i(TAG, "获取年龄结束")
        return 18
    }
    suspend fun getSex(): String {
        Log.i(TAG, "开始获取性别")
        delay(1000)
        Log.i(TAG, "获取性别结束")
        return "男"
    }
    /**
     * 串联请求
     */
    fun tandem() {
        CoroutineScope(CoroutineName("测试携程") + Job() + Dispatchers.Main)
            .launch {
                Log.i(TAG, "开始执行")
                val name = getName()
                Log.i(TAG, "姓名：$name ")
                val age = getAge()
                Log.i(TAG, "年龄：$age")
                Log.i(TAG, "结束执行")
            }
        Thread.sleep(5_000L)
    }

    /**
     * 并联请求
     */
    fun parallel() {
        CoroutineScope(CoroutineName("协程1") + Job() + Dispatchers.IO)
            .launch {
                Log.i(TAG, "开始执行")
                launch {
                    val name = getName()
                    Log.i(TAG, "姓名：$name ")
                }
                launch {
                    val age = getAge()
                    Log.i(TAG, "年龄：$age")
                }
                Log.i(TAG, "结束执行")
            }
        Thread.sleep(5_000L)
    }

    /**
     * 并联执行，统一处理返回值
     * 只有调用了await()方法，程序才会等待所有value获取后再统一处理
     */
    fun test1() {
       CoroutineScope(CoroutineName("协程1") + Job() + Dispatchers.IO)
            .launch {
                Log.i(TAG, "开始执行")
                val value1 = async {
                    getName()
                }
                Log.i(TAG,"delay只会挂起当前的携程")
                val value2 = async {
                    getAge()
                }
                val value3 = async {
                    getSex()
                }
                Log.i(TAG, "姓名：${value1.await()} 年龄：${value2.await()}")
                Log.i(TAG, "结束执行")
            }
    }

    /**
     * 模拟网络请求
     */
    fun test2() {
        CoroutineScope(Job() + Dispatchers.IO)
            .launch {
                //do something for http
                withContext(Dispatchers.Main) {
                    //update UI
                }
            }
    }

}