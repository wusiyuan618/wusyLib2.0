package com.wusy.wusypro.app

import android.os.Handler
import com.google.gson.Gson
import com.orhanobut.logger.Logger
import com.wusy.wusylibrary.util.OkHttpUtil
import com.wusy.wusypro.tool.BasicRecycleList.ProgramBean
import okhttp3.Call
import okhttp3.Response
import java.io.IOException

class ApplicationModel{
    fun getToolProList(handler: Handler, pageIndex:Int,programName:String){
        OkHttpUtil.getInstance().asynGet(URLUtil.getProgramList(pageIndex,programName),object :
            OkHttpUtil.ResultCallBack{
            override fun successListener(call: Call?, response: Response?) {
                val json=response!!.body()!!.string()
                try{
                    val bean= Gson().fromJson(json,
                        ProgramBean::class.java)
                    if(bean.status=="0")
                        Contants.sendMessageByHandler(handler,bean)
                    else
                        Contants.sendMessageByHandler(handler,bean.msg)
                }catch (e:Exception){
                    Logger.i("数据解析错误",json)
                    Contants.sendMessageByHandler(handler,e.localizedMessage)
                }
            }

            override fun failListener(call: Call?, e: IOException?, message: String?) {
                Contants.sendMessageByHandler(handler,e?.localizedMessage?:"")
            }

        })
    }
}
