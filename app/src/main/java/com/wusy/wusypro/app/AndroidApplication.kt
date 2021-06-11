package com.wusy.wusypro.app

import android.Manifest
import com.orhanobut.logger.Logger
import com.wusy.wusylibrary.base.BaseApplication
import com.wusy.wusylibrary.util.SharedPreferencesUtil
import com.wusy.wusylibrary.util.loggerExpand.LoggerSetting
import com.wusy.wusylibrary.util.loggerExpand.MyDiskLogAdapter
import com.wusy.wusylibrary.util.permissions.PermissionsManager
import com.wusy.wusylibrary.util.permissions.PermissionsResultAction
import com.wusy.wusypro.BuildConfig

class AndroidApplication : BaseApplication() {
    override fun onCreate() {
        super.onCreate()
        initService()
        initLoggerDis()
    }
    private fun initService(){
        if(SharedPreferencesUtil.getInstance(this).getData(Contants.ServiceIp,"")!=""){
            URLUtil.baseUrl= SharedPreferencesUtil.getInstance(this).getData(Contants.ServiceIp,"").toString()
        }else{
            URLUtil.baseUrl = "${if (BuildConfig.BUILD_TYPE == "release") "https://www.hjlapp.com/" else "https://t.hjlapp.com/"}"
        }
    }
    private fun initLoggerDis() {
        /**
         * 配置Logger存文件
         */
        if(PermissionsManager.getInstance().hasPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            LoggerSetting.fileName = "wusyProLogger"
            Logger.addLogAdapter(MyDiskLogAdapter(this))//保存到文件
            Logger.i("Logger本地日志模块初始化成功")
        }else{
            Logger.e("Logger本地日志模块初始化失败，没有给存储权限")
        }
    }
}
