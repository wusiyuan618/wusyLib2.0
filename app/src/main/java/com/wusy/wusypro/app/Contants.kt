package com.wusy.wusypro.app

import android.os.Handler

object Contants{
    //列表条数
    const val pageSize=12
    //存储个人设置的服务器地址
    const val ServiceIp="ServiceIp"
    //网络请求成功回调code
    const val OKHTTP_REQUEST_SUCCESS=0
    //网络请求失败回调code
    const val OKHTTP_REQUEST_ERROR=1

    /**
     *  网络请求发生错误时发送Handler消息
     */
    fun sendMessageByHandler(handler: Handler, error:String?){
        val msg=handler.obtainMessage()
        msg.what=OKHTTP_REQUEST_ERROR
        msg.obj=error
        handler.sendMessage(msg)
    }
    /**
     *  网络请求成功时发送Handler消息
     */
    fun sendMessageByHandler(handler: Handler, basicBean:BasicBean?){
        val msg=handler.obtainMessage()
        msg.what=OKHTTP_REQUEST_SUCCESS
        msg.obj=basicBean
        handler.sendMessage(msg)
    }
}
